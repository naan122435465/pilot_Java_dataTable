$(document).ready(function(){
	const bar = document.getElementById('bar');
	const close = document.getElementById('close');
	const nav = document.getElementById('navbar');

	if (bar) {
		bar.addEventListener('click', () => {
			nav.classList.add('active')
		})
	}

	if (close) {
		close.addEventListener('click', () => {
			nav.classList.remove('active')
		})
	}
// Show Shopping Cart Modal
	$('#lg-bag').on('click',function(){
		$('#shoppingCart').modal('show');
		productInCart();
	})
// Update quantity in Cart
	$('#cartRow').on('change',' tr td input', function(){
		var quantity = $(this).val()
		var idrow =  $(this).closest('tr').attr('id')
		$.ajax({
			url:'/cart/api/update?id='+idrow+'&quantity='+quantity,
			dataType:'json',
			contentType: 'application/json',success: function(responseData){
				if(responseData.responseCode == 100){
					$('#cartRow').empty();
					productInCart();					
				}				
			}
		})		
	})
// Remove product in cart

	$('#cartRow').on('click','tr td i',function(){
		var row = $(this).closest('tr')
		var idrow =  row.attr('id');
		$.ajax({
			url:'/cart/api/delete?id='+idrow,
			dataType:'json',
			contentType: 'application/json',
			success: function(responseData){
				if(responseData.responseCode == 100){
					$('#cartRow').empty();
					productInCart();					
				}				
			}
		})				
	})
})
// Product in cart
function productInCart(){
	$.ajax({
			url:'/cart/api/shoppingcart',
			type:'GET',
			dataType:'json',
			contentType: 'application/json',
			success:function(responseData){				 
       			if (responseData.responseCode == 100){
	
					renderShoppingCart(responseData.data.cart);
				}
       			
			}
		})
}
// Render HTML For Shopping Cart

function renderShoppingCart(cartList){
	var rowHtml = "";
	var total = 0;
	$('#cartRow').empty();
	$.each(cartList, function(key, value){
		console.log(value);
		var price = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value.productEntity.price);
		var subtotal = value.productEntity.price * value.quantity;
		var subtotalConvert = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(subtotal);
		total += subtotal;
		rowHtml = '<tr id="'+ value.productEntity.productId +'">'+
					'<td><i class="fa-solid fa-x"></i></i></td>'+
					'<td colspan="2"><img src="'+value.productEntity.image+'" alt="">'+
					'<p>'+value.productEntity.productName+'</p>'+
					'</td>'+					
					'<td>'+price+'</td>'+
					'<td><input type="number" value="'+value.quantity +'" min="0"  ></td>'+
					'<td>'+subtotalConvert+'</td>'+
				'</tr>';
		$('#cartRow').append(rowHtml);
	})
	var totalConvert = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(total);
	$('#total').text(totalConvert);
	$('#totalFinal').text(totalConvert);
}