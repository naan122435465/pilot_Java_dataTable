$(document).ready(function() {
	// Add class = active in the nav
	$('#navbar').children(':nth-child(1)').children().attr("class","active");
	findTop10NewProduct();
	random10Product(); 
	// Header to Product Details
	$('#topNewProduct').on('click','.pro', function(){
		var id =  $(this).attr('id');
		console.log(id);
		window.location.href="/productDetails?id="+id;
	})
})

/**
* Top 10 New Products
*/
function findTop10NewProduct() {
	$.ajax({
		url: "/api/getTop10NewProducts",
		dataType: 'json',
		contentType: 'application/json',
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				var topnewProduct = $('#topNewProduct');
				renderProductsCard(responseData.data.productList,topnewProduct);
			}
		}
	})
}
/**
* Random 10 product
*/
function random10Product(){
	$.ajax({
		url: "/api/get10RandomProducts",
		dataType: 'json',
		contentType: 'application/json',
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				var random10Product = $('#random10Product');
				renderProductsCard(responseData.data.productList,random10Product);
			}
		}
	})
}
/**
* Render HTML for product card
*/
function renderProductsCard(productList, render) {
	var rowHtml = "";
	render.empty();
	$.each(productList, function(key, value) {
		var price = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value.price)
		rowHtml =  '<div class="pro" id = "' + value.productId + '">' +
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
				'</div>' ;
		render.append(rowHtml);
	})
}