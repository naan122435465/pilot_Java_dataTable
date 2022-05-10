$(document).ready(function() {
	$('#navbar').children(':nth-child(3)').children().attr("class","active");
	var $orderInfoForm = $('#orderInfoForm');
	var $orderInfoModal = $('#orderInfoModal');
	var $orderTable;
	// Show orders list when opening page
	searchOrder();

	// Show orders list when have searching even
	$('#keyword').keyup(function() {
		$orderTable.destroy();
		searchOrder();

	});
	$('#dateFrom').change(function() {
		$orderTable.destroy();
		searchOrder();

	});
	$('#dateTo').change(function() {
		$orderTable.destroy();
		searchOrder();

	});
	// update order  status  modal when click btn
	$('#orderInfoTable').on('click', 'tbody tr .btn-pedding', function() {
		var $tr = $(this).closest('tr');
		var dataRow = $orderTable.row($tr).data();
		updateOrderStatus(dataRow.orderId, 0);
		
	});
	$('#orderInfoTable').on('click', 'tbody tr .btn-confirmed', function() {
		var $tr = $(this).closest('tr');
		var dataRow = $orderTable.row($tr).data();
		updateOrderStatus(dataRow.orderId, 1);
		
	});
	$('#orderInfoTable').on('click', 'tbody tr .btn-cancelled', function() {
		var $tr = $(this).closest('tr');
		var dataRow = $orderTable.row($tr).data();
		updateOrderStatus(dataRow.orderId, 2);
		
	});


	// Show details modal
	$('#orderInfoTable').on('click', 'tbody tr .detail-btn', function() {
		//resetFormModal('#detailsOrderModal');
		var $tr = $(this).closest('tr');
		var dataRow = $orderTable.row($tr).data();

		$.ajax({
			url: '/orderDetails/api/orderDetailList?id=' + dataRow.orderId,
			type: "GET",
			contentType: 'application/json',
			success: function(responseData) {
				if (responseData.responseCode == 100) {
					$('#detailsOrderModal').modal('show');
					$('#cartRow').empty();
					renderDetailsOrder(responseData.data.data);

				}
			}
		})
	})



	// Submit delete order
	$('#deleteSubmitBtn').on('click', function() {
		$.ajax({
			url: '/orders/api/delete?id=' + $(this).attr('data-id'),
			type: 'DELETE',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				$('#confirmDeleteModal').modal('hide');
				showNotification(responseData.responseCode == 100, responseData.responseMsg);
				$orderTable.destroy();
				searchOrder();
			}

		})
	})




	//get Order List
	function searchOrder() {

		$orderTable = $('#orderInfoTable').DataTable({
			"processing": true,
			"serverSide": true,
			searching: false,
			"ajax": {
				url: '/orders/api/orderList',
				dataType: 'json',
				contentType: 'application/json; charset=utf-8',
				type: 'POST',
				data: function(setting) {
					var data = {
						'length': setting.length,
						'start': setting.start,
						'sSortDir': setting.order[0].dir,
						'draw': setting.draw,
						'iSortColumn': setting.columns[0].name,
						'iSortNum': setting.order[0].column,
						'keyword': $('#keyword').val(),
						'dateFrom': $('#dateFrom').val(),
						'dateTo': $('#dateTo').val(),

					}
					return JSON.stringify(data);
				}
			},
			columnDefs: [
				{
					targets: 0,
					orderable: false,
					render: function(type, row) {
						return "<a class='detail-btn btn'><i class='fas fa-edit'> </a>";
					}
				},
				{
					data: 'customerName',
					targets: 1,
				},
				{
					data: 'customerPhone',
					targets: 2,
				},
				{
					data: 'customerAddress',
					targets: 3,
				},
				{
					data: 'customerEmail',
					targets: 4,
				},
				{
					data: 'saleDateFormat',
					targets: 5,
				},
				{
					data: 'amount',
					targets: 6,
					render: function(data, type) {
						var number = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(data);
						return number;
					}
				},
				{
					data: 'orderStatus',
					targets: 7,
					render: function(data, type) {
						var orderStatus;
						switch (data) {
							case 0:
								orderStatus = "Pendding"
								break;
							case 1:
								orderStatus = "Confirmed"
								break;
							case 2:
								orderStatus = "Cancelled"
								break;
						}
						return  orderStatus ;
					}
				},
				{
					targets: 8,
					orderable: false,
					render: function(type, row) {
						return '<button class="normal btn-pedding">Pendding </button><button class="normal btn-confirmed">Confirmed </button><button class="normal btn-cancelled">Cancelled </button>';
					}
				},
				

			]

		})
	}
// Update Order Status 
 function updateOrderStatus(id,orderStatus) {	
		$.ajax({
			url: '/orders/api/update?id='+id+'&orderStatus='+orderStatus,
			type: 'POST',
			processData: false,
			contentType: false,
			cache: false,
			timeout: 10000,
			success: function(responseData) {
				if (responseData.responseCode == 100) {
					$orderTable.destroy();
					searchOrder();
					showNotification(true, responseData.responseMsg);
				} else {
					showMsgOnField($orderInfoForm.find('#customerName'), responseData.responseMsg);
				}
			}
		})


	}
})



function renderDetailsOrder(detailsList) {
	var totalIndex = 0;
	$.each(detailsList, function(key, value) {
		var price = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value.productEntity.price);
		var subtotal = value.productEntity.price * value.quanity;
		var subtotalConvert = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(subtotal);
		totalIndex += subtotal;
		rowHtml = '<tr id="' + value.productEntity.productId + '">' +
			'<td><i class="fa-solid fa-x"></i></i></td>' +
			'<td colspan="2"><img src="' + value.productEntity.image + '" alt="">' +
			'<p>' + value.productEntity.productName + '</p>' +
			'</td>' +
			'<td>' + price + '</td>' +
			'<td>' + value.quanity + '</td>' +
			'<td>' + subtotalConvert + '</td>' +
			'</tr>';
		$('#cartRow').append(rowHtml);
	})
	var totalConvert = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(totalIndex);
	$('#totalFinal').text(totalConvert);
	$('#totalFinal').text(totalConvert);
}