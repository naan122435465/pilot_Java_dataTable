
$(document).ready(function() {

	var $productInfoForm = $('#productInfoForm');
	var $productInfoModal = $('#productInfoModal');
	var $productTable;
	// Show products list when opening page
	searchProduct();
	// Show products list when open edit/add product
	brandList();
	// Show products list when have searching even
	$('#keyword').keyup(function() {
		$productTable.destroy();
		searchProduct();
	});
	$('#priceTo').keyup(function() {
		$productTable.destroy();
		searchProduct();
	});
	$('#priceFrom').keyup(function() {
		$productTable.destroy();
		searchProduct();
	});


	// Show add product modal
	$('#addProductInfoModal').on('click', function() {
		resetFormModal($productInfoForm);
		showModalWithCustomizedTitle($productInfoModal, "Add Product");
		$('#image img').attr('src', '/images/image-demo.png');
		$('#productId').closest(".form-group").addClass("d-none");
		$("#productImage .required-mask").removeClass("d-none");
	});

	// Show update product modal
	$("#productInfoTable").on('click', 'tbody tr .edit-btn', function() {

		$("#productImage .required-mask").addClass("d-none");
		resetFormModal($productInfoForm);
		showModalWithCustomizedTitle($productInfoModal, "Edit Brand");
		var $tr = $(this).closest('tr');
		var dataRow = $productTable.row($tr).data();
		$('#productId').val(dataRow.productId);
		$('#productName').val(dataRow.productName);
		$('#quantity').val(dataRow.quantity);
		$('#price').val(dataRow.price);
		$('#brandId').val(dataRow.brandEntity.brandId)
		$('#saleDate').val(dataRow.saleDate);
		$('#description').val(dataRow.description);

		var productImage = dataRow.image;
		if (productImage == null || productImage == "") {
			productImage = "/images/image-demo.png";
		}
		$("#image img").attr("src", productImage);
		$("#logo").val(productImage);


	});



	//Show img product modal
	$("#productInfoTable").on('click', 'tbody tr img', function() {

		$('#img01').attr('src', this.src);
		$('#imgModal').modal('show');

	})


	// Show delete product confirmation modal
	$("#productInfoTable").on('click', 'tbody tr .delete-btn', function() {
		var $tr = $(this).closest('tr');
		var dataRow = $productTable.row($tr).data();
		$("#deleteproductname").text(dataRow.productName);
		$("#deleteSubmitBtn").attr("data-id", dataRow.productId);
		$('#confirmDeleteModal').modal('show');
	});
	// Submit delete product

	$("#deleteSubmitBtn").on('click', function() {
		$.ajax({
			url: "/product/api/delete/" + $(this).attr("data-id"),
			type: 'DELETE',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				$('#confirmDeleteModal').modal('hide');
				showNotification(responseData.responseCode == 100, responseData.responseMsg);
				$productTable.destroy();
				searchProduct();
			}
		});
	});
	// Submit add and update product
	$('#saveProductBtn').on('click', function(event) {

		event.preventDefault();
		var formData = new FormData($productInfoForm[0]);
		var productId = formData.get("productId");
		var isAddAction = productId == undefined || productId == "";

		$productInfoForm.validate({
			ignore: [],
			rules: {
				productName: {
					required: true,
					maxlength: 100
				},

				imageFiles: {
					required: isAddAction,
				}
			},
			messages: {
				productName: {
					required: "Please input Product Name",
					maxlength: "The Product Name must be less than 100 characters",
				},

				imageFiles: {
					required: "Please upload Product Image",
				}
			},
			errorElement: "div",
			errorClass: "error-message-invalid"
		});

		if ($productInfoForm.valid()) {

			// POST data to server-side by AJAX
			$.ajax({
				url: "/product/api/" + (isAddAction ? "add" : "update"),
				type: 'POST',
				enctype: 'multipart/form-data',
				processData: false,
				contentType: false,
				cache: false,
				timeout: 10000,
				data: formData,
				success: function(responseData) {

					// Hide modal and show success message when save successfully
					// Else show error message in modal
					if (responseData.responseCode == 100) {
						$productInfoModal.modal('hide');
						$productTable.destroy();
						searchProduct();
						showNotification(true, responseData.responseMsg);
					} else {
						showMsgOnField($productInfoForm.find("#productName"), responseData.responseMsg);
					}
				}
			});
		}
	});

// get Product List
	function searchProduct() {
		$productTable = $('#productInfoTable').DataTable({
			"processing": true,
			"serverSide": true,
			searching: false,
			"ajax": {
				url: 'product/api/productList',
				dataType: 'json',
				contentType: 'application/json; charset=utf-8',
				type: "POST",
				data: function(setting) {

					var data = {

						'length': setting.length,
						'start': setting.start,
						'sSortDir': setting.order[0].dir,
						'draw': setting.draw,
						'iSortColumn': setting.columns[0].name,
						'iSortNum': setting.order[0].column,
						"priceFrom": $('#priceFrom').val(),
						"priceTo": $('#priceTo').val(),
						"keyword": $('#keyword').val(),
						//'searchCondition': searchCondition
					}

					return JSON.stringify(data);
				},


			},
			columnDefs: [

				{
					data: 'productId',
					targets: 0,
				},
				{
					data: 'productName',
					targets: 1,
				},
				{
					data: 'quantity',
					targets: 2,
				},
				{
					data: 'price',
					targets: 3,
					render: function(data, type) {
						var number = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(data);
						return number;
					}


				},
				{
					data: 'brandEntity.brandName',
					targets: 4,
				},
				{
					data: 'saleDate',
					targets: 5,
				},
				{
					data: 'image',
					targets: 6,
					orderable: false,
					render: function(data, type, row, meta) {
						return '<img src="' + data + '"id ="imgproduct">'
					}

				},
				{
					targets: 7,
					orderable: false,
					render: function(type, row) {
						return "<a class='edit-btn btn'><i class='fas fa-edit'></i></a> | <a class='delete-btn btn' ><i class='fas fa-trash-alt'></i></a>";
					}
				}
			]

		})
	}
});
// Get Brand List
function brandList() {
	$.ajax({
		url: "/product/api/brandList",
		dataType: 'json',
		contentType: 'application/json',
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				renderBrandName(responseData.data);
			}
		}
	})
}
// Render Brand Name
function renderBrandName(brandList) {
	var brandNameHtml = "";
	$.each(brandList, function(key, value) {
		brandNameHtml = "<option value='" + value.brandId + "'>" + value.brandName + "</option>";
		$("#brandId").append(brandNameHtml);
	});
}