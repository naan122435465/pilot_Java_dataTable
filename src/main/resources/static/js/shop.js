$(document).ready(function() {
	// Add class = active in the nav
	$('#navbar').children(':nth-child(2)').children().attr("class","active");
	// Show products list when opening page
	findProducts(1);
	brandList();

	//Show products list when clicking button search
	
	$('#keyword').keyup(function() {
		findProducts(1);
	});

	$('#brandName').on('change', function() {
		findProducts(1);

	});
	$('#price').on('change', function() {
		findProducts(1);

	});	
	
	// Show products list when clicking pagination button
	$('.pagination').on('click', '.page-link', function() {
		var pagerNumber = $(this).attr("data-index");
		findProducts(pagerNumber);
	})
	
	// Header to Product Details
	$('#productList').on('click','.pro', function(){
		var id =  $(this).attr('id');
		window.location.href="/productDetails?id="+id;
	})
})
/**
* Find Products
*/

function findProducts(pagerNumber) {
	var data = {
		"keyword": $('#keyword').val(),
		"priceFrom": $('#price option:selected').attr('data-priceFrom'),
		"priceTo": $('#price option:selected').attr('data-priceTo'),
		"brandName": $('#brandName').val()
	}

	$.ajax({
		url: "/api/search/" + pagerNumber,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				renderProductsCard(responseData.data.productList);
				renderPagination(responseData.data.paginationInfo);
			}
		}
	});
}
function renderPagination(paginationInfo) {

	var paginationInnerHtml = "";
	if (paginationInfo.pageNumberList.length > 0) {
		$("ul.pagination").empty();
		paginationInnerHtml += '<li class="page-item"><a class="page-link ' + (paginationInfo.firstPage == 0 ? 'disabled' : '') + '" href="javascript:void(0)" data-index="' + paginationInfo.firstPage + '">First</a></li>'
		paginationInnerHtml += '<li class="page-item"><a class="page-link ' + (paginationInfo.previousPage == 0 ? 'disabled' : '') + '" href="javascript:void(0)" data-index="' + paginationInfo.previousPage + '"> < </a></li>'
		$.each(paginationInfo.pageNumberList, function(key, value) {
			paginationInnerHtml += '<li class="page-item"><a class="page-link ' + (value == paginationInfo.currentPage ? 'active' : '') + '" href="javascript:void(0)" data-index="' + value + '">' + value + '</a></li>';
		});
		paginationInnerHtml += '<li class="page-item"><a class="page-link ' + (paginationInfo.nextPage == 0 ? 'disabled' : '') + '" href="javascript:void(0)" data-index="' + paginationInfo.nextPage + '"> > </a></li>'
		paginationInnerHtml += '<li class="page-item"><a class="page-link ' + (paginationInfo.lastPage == 0 ? 'disabled' : '') + '" href="javascript:void(0)" data-index="' + paginationInfo.lastPage + '">Last</a></li>'
		$("ul.pagination").append(paginationInnerHtml);
	}

}

/**
* Get Brand List
*/
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
/**
* Render HTML for Product Card
*/
function renderProductsCard(productList) {
		console.log(productList);
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
				'</div>' ;
		$('#productList').append(rowHtml);
	})
}
/**
* Render HTML for BranName List
*/

function renderBrandName(brandList) {
	var brandNameHtml = "";
	$.each(brandList, function(key, value) {
		brandNameHtml = "<option value='" + value.brandName + "'>" + value.brandName + "</option>";
		$("#brandName").append(brandNameHtml);
	});
}