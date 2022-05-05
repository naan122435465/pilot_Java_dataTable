	var totalIndex;
$(document).ready(function() {
	// Add class = active in the nav
	$('#navbar').children(':nth-child(6)').children().attr("class","active");
	// Show product in Cart
	productInCartIndex();

	// Update quantity in Cart
	$('#finalCart').on('change',' tr td input', function(){
		console.log("update");
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
		console.log(totalIndex);
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

				},
				customerPhone: {
					required: true,
					regex: /^0+[0-9]{8}/,
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
				ccustomerName: {
					required: "Please input Your Name",

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
						window.location.href="http://localhost:8880/home";
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

		var price = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value.productEntity.price);
		var subtotal = value.productEntity.price * value.quality;
		var subtotalConvert = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(subtotal);
		totalIndex += subtotal;
		rowHtml = '<tr id="' + value.productEntity.productId + '">' +
			'<td><i class="fa-solid fa-x"></i></i></td>' +
			'<td><img src="' + value.productEntity.image + '" alt=""></td>' +
			'<td>' + value.productEntity.productName + '</td>' +
			'<td>' + price + '</td>' +
			'<td><input type="number" value="' + value.quality + '" min="0"  ></td>' +
			'<td>' + subtotalConvert + '</td>' +
			'</tr>';
		$('#finalCart').append(rowHtml);
	})
	var totalConvert = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(totalIndex);
	$('#finalTotal1').text(totalConvert);
	$('#finalTotal2').text(totalConvert);
}