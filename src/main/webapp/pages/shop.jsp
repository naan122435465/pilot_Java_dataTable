<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<jsp:include page="../common/head.jsp" />
<link rel="stylesheet" href="<c:url value='/css/baseWeb.css'/>">
<link rel="stylesheet" href="<c:url value='/css/shop.css'/>">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

</head>
<body>
	<jsp:include page="../common/headerWeb.jsp" />
	<section id="page-header">
		<h2>#stayhome</h2>
		<p>Save more with coupons & up to 70% off!</p>
	</section>
	<section id="box-filter" class="section-p1">
		<div class="brandName-filter">
			<select name="brandName" id="brandName">
				<option value="">Hãng</option>

			</select>
		</div>
		<div class="price-filter">
			<select id="price" name='price'>
				<option selected="selected" value="">Giá</option>
				<option data-priceFrom="" data-priceTo="2000000">Dưới 2
					triệu</option>
				<option data-priceFrom="2000000" data-priceTo="4000000">Từ
					2 -4 triệu</option>
				<option data-priceFrom="4000000" data-priceTo="7000000">Từ
					4 - 7 triệu</option>
				<option data-priceFrom="7000000" data-priceTo="13000000">Từ
					7 - 13 triệu</option>
				<option data-priceFrom="13000000" data-priceTo="20000000">Từ
					13 - 20 triệu</option>
				<option data-priceFrom="20000000" data-priceTo="">trên 20tr</option>

			</select>
		</div>
		<div class="name-filter">
			<input id="keyword" name='keyword' type="text"
				placeholder="Tên Sản Phẩm">
		</div>
	</section>
	<section id="product1" class="section-p1">
		<h2>Featured Products</h2>
		<p>Summer Collection New Morden Design</p>
		<div class="pro-container" id="productList"></div>
	</section>


	<div class="d-flex justify-content-center" class="section-p1">
		<ul class="pagination">

		</ul>
	</div>


	<jsp:include page="../common/footerWeb.jsp" />
	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/shop.js'/>"></script>
	<script src="<c:url value='/js/baseWeb.js'/>"></script>
</body>
</html>