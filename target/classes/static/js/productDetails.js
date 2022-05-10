$(document).ready(function() {
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
	// Show top 10 product
	findTop10NewProduct();

	//Show product Details
	var url = new URL(window.location.href);
	var id = url.searchParams.get("id")
	productDeatails(id);

	// Header to Product Details when click on product card
	$('#productList').on('click', '.pro', function() {
		var id = $(this).attr('id');
		console.log(id);
		window.location.href = "/productDetails?id=" + id;
	})
	
	// Add to cart
	$('#prodetails').on('click','button', function(){
		var quantity = $('#quantity').val();
		$.ajax({
			url:'/cart/api/add?id='+id+'&quantity='+quantity,
			dataType:'json',
			contentType: 'application/json',
			success: function(responseData){
				showNotification(responseData.responseCode == 100, responseData.responseMsg);
			}
		})
	})
})
/**
* Product Details
*/
function productDeatails(id) {
	$.ajax({
		url: "/productDetailsApi?id=" + id,
		dataType: 'json',
		contenType: 'application/json',
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				var product = responseData.data.product;
				var price = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(product.price);
				$("#productName").text(product.productName)
				$("#price").text(price)
				$("#description").text(product.description)
				$("#mainImg").attr("src", product.image);

			}
		}
	})
}
/**
* Top 10 New Products
*/
function findTop10NewProduct() {
	$.ajax({
		url: "/api/get10RandomProducts",
		dataType: 'json',
		contentType: 'application/json',
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				renderProductsCard(responseData.data.productList);
			}
		}
	})
}

/**
* Render HTML for product card
*/
function renderProductsCard(productList) {
	var rowHtml = "";
	$('#productList').empty();
	$.each(productList, function(key, value) {
		var price = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value.price)
		rowHtml = '<div class="pro" id = "' + value.productId + '">' +
			'<img src="' + value.image + '" alt="">' +
			'<div class="des">' +
			'<span>' + value.productName + '</span>' +
			'<h5>The most new product</h5>' +
			'<div class="star">' +
			'<i class="fa fa-star"></i>' +
			'<i class="fa fa-star"></i>' +
			'<i class="fa fa-star"></i>' +
			'<i class="fa fa-star"></i>' +
			'<i class="fa fa-star"></i>' +
			'</div>' +
			'<h4>' + price + '</h4>' +
			'</div>' +
			'<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>' +
			'</div>';
		$('#productList').append(rowHtml);
	})
}