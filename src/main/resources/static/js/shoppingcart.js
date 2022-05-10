var totalIndex;
$(document).ready(function() {
	// Add class = active in the nav
	$('#navbar').children(':nth-child(6)').children().attr("class","active");
	// Show product in Cart
	productInCartIndex();

	// Update quantity in Cart
	$('#finalCart').on('change',' tr td input', function(){
	
		var quantity = $(this).val()
		var idrow =  $(this).closest('tr').attr('id')
		$.ajax({
			url:'/cart/api/update?id='+idrow+'&quantity='+quantity,
			dataType:'json',
			contentType: 'application/json',success: function(responseData){
				if(responseData.responseCode == 100){
					$('#finalCart').empty();
					productInCartIndex();					
				}				
			}
		})		
	})
	// Remove product in cart

	$('#finalCart').on('click','tr td i',function(){
		var row = $(this).closest('tr')
		var idrow =  row.attr('id');
		$.ajax({
			url:'/cart/api/delete?id='+idrow,
			dataType:'json',
			contentType: 'application/json',
			success: function(responseData){
				if(responseData.responseCode == 100){
					$('#finalCart').empty();
					productInCartIndex();					
				}				
			}
		})				
	})
	// Submit checkout cart.
	$('#addOrder').on('click', function(event) {
		event.preventDefault();
		var $customerInfo = $('#customerInfo');
		var formData = new FormData($customerInfo[0]);
		formData.append('amount',totalIndex);
		$.validator.addMethod(
			"regex",
			function(value, element, regexp) {
				return this.optional(element) || regexp.test(value);
			},
			"Please check your input."
		);
		$customerInfo.validate({
			ignore: [],
			rules: {
				customerName: {
					required: true,
					regex:/[a-z]|[A-Z]/,
				},
				customerPhone: {
					required: true,
					regex: /^[0]{1}(3|[7-9]){1}[0-9]{8}$/,
				},
				customerEmail: {
					required: true,
					regex: /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$/,
				},
				customerAddress: {
					required: true,

				}
			},
			messages: {
				customerName: {
					required: "Please input Your Name",
					regex: "Please input Your Name"

				},
				customerPhone: {
					required: "Please input Your Phone Number",
					regex: "Please enter a valid Phone Number"
				},
				customerEmail: {
					required: "Please input Your Email",
					regex: "Please enter a valid email address.",
				},
				customerAddress: {
					required: "Please input Your Address",

				}
			}

		});
		if ($customerInfo.valid()) {
			
			$.ajax({
				url: "/cart/api/addOrder",
				type: "POST",
				processData: false,
				contentType: false,
				cache: false,
				timeout: 10000,
				data: formData,
				success: function(responseData) {
					if (responseData.responseCode == 100) {
						showNotification(responseData.responseCode == 100, responseData.responseMsg);
						renderOrderSuccessModal(responseData.data.orders,responseData.data.orderDetails);
						console.log(responseData.data);
						
					}
				}
			});
		}
	});
});


// Product in cart
function productInCartIndex() {

	$.ajax({
		url: '/cart/api/shoppingcart',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json',
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				renderShoppingCartIndex(responseData.data.cart);
			}

		}
	})
}

// Render HTML For Shopping Cart
function renderShoppingCartIndex(cartList) {
	
	var rowHtml = "";
	totalIndex = 0;
	$('#finalCart').empty();
	$.each(cartList, function(key, value) {
	console.log(value);
		var price = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value.productEntity.price);
		var subtotal = value.productEntity.price * value.quantity;
		var subtotalConvert = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(subtotal);
		totalIndex += subtotal;
		rowHtml = '<tr id="' + value.productEntity.productId + '">' +
			'<td><i class="fa-solid fa-x"></i></i></td>' +
			'<td><img src="' + value.productEntity.image + '" alt=""></td>' +
			'<td>' + value.productEntity.productName + '</td>' +
			'<td>' + price + '</td>' +
			'<td><input type="number" value="' + value.quantity + '" min="0"  ></td>' +
			'<td>' + subtotalConvert + '</td>' +
			'</tr>';
		$('#finalCart').append(rowHtml);
	})
	var totalConvert = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(totalIndex);
	$('#finalTotal1').text(totalConvert);
	$('#finalTotal2').text(totalConvert);
}

// Render HTML For Order Success Modal
function renderOrderSuccessModal(orders,orderDetails){
	$('#orderSuccessModal').modal('show');
	$('#customerNameOrderSuccess').val(orders.customerName);
	$('#customerAddressOrderSuccess').val(orders.customerAddress);
	$('#customerEmailOrderSuccess').val(orders.customerEmail);
	$('#customerPhoneOrderSuccess').val(orders.customerPhone);
	$('#orderDateOrderSuccess').val(orders.saleDateFormat);
	var rowHtml = "";
	$('#cartRowOrderSuccess').empty();
	$.each(orderDetails, function(key, value) {
		var price = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value.productEntity.price);
		var subtotalConvert = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value.amount);
		totalIndex += subtotal;
		rowHtml = '<tr id="' + value.productEntity.productId + '">' +
			'<td><i class="fa-solid fa-x"></i></i></td>' +
			'<td colspan="2"><img src="'+value.productEntity.image+'" alt="">'+
				'<p>'+value.productEntity.productName+'</p>'+
			'</td>'+	
			'<td>' + price + '</td>' +
			'<td>'+ value.quanity +'</td>' +
			'<td>' + subtotalConvert + '</td>' +
			'</tr>';
		$('#cartRowOrderSuccess').append(rowHtml);
	})
	var totalConvert = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(orders.amount);
	$('#totalOrderSuccess').text(totalConvert);
}