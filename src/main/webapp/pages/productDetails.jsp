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
<link rel="stylesheet" href="<c:url value='/css/productDetails.css'/>">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

</head>
<body>
<jsp:include page="../common/headerWeb.jsp" />
	<section id="prodetails" class="section-p1">
		<div class="single-pro-image" >
			<img src="image/product1.jpg" width="100%" id="mainImg" alt="">
		</div>
		<div class="single-pro-details">
			<h4 id = "productName">Men's Fashion T Shirt</h4>
			<h2 id = "price">$139.00</h2>
			<input type="number" value="1" id= "quantity">
			<button class=" normal">Add To Cart</button>
			 <a href="http://localhost:8880/shoppingCart"><button class=" normal checkout">Checkout</button></a>
			<h4>Product Details</h4>
			<span id= "description">The Gildan Ultra Cotton T-Shirt is made from a
				substantial 6.0 oz. per sq. yd. fabric constructed from 100% cotton,
				this classic fit preshrunk jersey knit provides unmatched comfort
				with each wear. Featuring a taped neck and shoulder, and a seamless
				double-needle collar, and available in a range of colors, it offers
				it all in the ultimate head-turing package </span>
		</div>
	</section>

	<section id="product1" class="section-p1">
		<h2>Featured Products</h2>
		<p>Summer Collection New Morden Design</p>
		<div class="pro-container" id = "productList"></div>
	</section>
	<jsp:include page="../common/footerWeb.jsp" />
	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/productDetails.js'/>"></script>
	<script src="<c:url value='/js/baseWeb.js'/>"></script>
</body>
</html>