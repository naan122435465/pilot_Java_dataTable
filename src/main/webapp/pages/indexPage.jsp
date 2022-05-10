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
<link rel="stylesheet" href="<c:url value='/css/indexPage.css'/>">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

</head>
<body>
	<jsp:include page="../common/headerWeb.jsp" />
	<section id="hero">
		<h4>Trade-in-offer</h4>
		<h2>Super value deals</h2>
		<h1>On all products</h1>
		<p>Save more with coupons & up to 70% off!</p>
		<button>Shop Now</button>
	</section>

	<section id="feature" class="section-p1">
		<div class="fe-box">
			<img src="images/feature1.jpg" alt="">
			<h6>Free Shipping</h6>
		</div>
		<div class="fe-box">
			<img src="images/feature2.png" alt="">
			<h6>Online Order</h6>
		</div>
		<div class="fe-box">
			<img src="images/feature3.png" alt="">
			<h6>Notification</h6>
		</div>
		<div class="fe-box">
			<img src="images/feature4.png" alt="">
			<h6>Saving money</h6>
		</div>
		<div class="fe-box">
			<img src="images/feature5.png" alt="">
			<h6>24/7 Service</h6>
		</div>
		<div class="fe-box">
			<img src="images/feature5.png" alt="">
			<h6>Free Shipping</h6>
		</div>
	</section>

	<section id="product1" class="section-p1">
		<h2>Featured Products</h2>
		<p>Summer Collection New Morden Design</p>
		<div class="pro-container" id="topNewProduct">
			<div class="pro">
				<img src="images/product1.jpg" alt="">
				<div class="des">
					<span>iphone 14</span>
					<h5>The most new product</h5>
					<div class="star">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
					</div>
					<h4>$78</h4>
				</div>
				<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
			</div>
			<div class="pro">
				<img src="images/product2.jpg" alt="">
				<div class="des">
					<span>iphone 14</span>
					<h5>The most new product</h5>
					<div class="star">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
					</div>
					<h4>$78</h4>
				</div>
				<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
			</div>
			<div class="pro">
				<img src="images/product3.jpg" alt="">
				<div class="des">
					<span>iphone 14</span>
					<h5>The most new product</h5>
					<div class="star">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
					</div>
					<h4>$78</h4>
				</div>
				<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
			</div>
			<div class="pro">
				<img src="images/product1.jpg" alt="">
				<div class="des">
					<span>iphone 14</span>
					<h5>The most new product</h5>
					<div class="star">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
					</div>
					<h4>$78</h4>
				</div>
				<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
			</div>
			<div class="pro">
				<img src="images/product2.jpg" alt="">
				<div class="des">
					<span>iphone 14</span>
					<h5>The most new product</h5>
					<div class="star">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
					</div>
					<h4>$78</h4>
				</div>
				<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
			</div>
			<div class="pro">
				<img src="images/product3.jpg" alt="">
				<div class="des">
					<span>iphone 14</span>
					<h5>The most new product</h5>
					<div class="star">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
					</div>
					<h4>$78</h4>
				</div>
				<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
			</div>
			<div class="pro">
				<img src="images/product1.jpg" alt="">
				<div class="des">
					<span>iphone 14</span>
					<h5>The most new product</h5>
					<div class="star">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
					</div>
					<h4>$78</h4>
				</div>
				<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
			</div>
		</div>
	</section>
	<section id="banner" class="section-p1">
		<h4>Repair Services</h4>
		<h2>
			Up to <span>70% Off</span> All Smart Phone Accessories
		</h2>
		<button class="normal">Explore More</button>
	</section>
	<section id="product1" class="section-p1">
		<h2>New Arrivals</h2>
		<p>Summer Collection New Morden Design</p>
		<div class="pro-container" id ="random10Product">
			<div class="pro">
				<img src="images/product1.jpg" alt="">
				<div class="des">
					<span>iphone 14</span>
					<h5>The most new product</h5>
					<div class="star">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
					</div>
					<h4>$78</h4>
				</div>
				<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
			</div>
			<div class="pro">
				<img src="images/product2.jpg" alt="">
				<div class="des">
					<span>iphone 14</span>
					<h5>The most new product</h5>
					<div class="star">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
					</div>
					<h4>$78</h4>
				</div>
				<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
			</div>
			<div class="pro">
				<img src="images/product3.jpg" alt="">
				<div class="des">
					<span>iphone 14</span>
					<h5>The most new product</h5>
					<div class="star">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
					</div>
					<h4>$78</h4>
				</div>
				<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
			</div>
			<div class="pro">
				<img src="images/product1.jpg" alt="">
				<div class="des">
					<span>iphone 14</span>
					<h5>The most new product</h5>
					<div class="star">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
					</div>
					<h4>$78</h4>
				</div>
				<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
			</div>
			<div class="pro">
				<img src="images/product2.jpg" alt="">
				<div class="des">
					<span>iphone 14</span>
					<h5>The most new product</h5>
					<div class="star">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
					</div>
					<h4>$78</h4>
				</div>
				<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
			</div>
			<div class="pro">
				<img src="images/product3.jpg" alt="">
				<div class="des">
					<span>iphone 14</span>
					<h5>The most new product</h5>
					<div class="star">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
					</div>
					<h4>$78</h4>
				</div>
				<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
			</div>
			<div class="pro">
				<img src="images/product1.jpg" alt="">
				<div class="des">
					<span>iphone 14</span>
					<h5>The most new product</h5>
					<div class="star">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
					</div>
					<h4>$78</h4>
				</div>
				<a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
			</div>
		</div>
	</section>

	<section id="sm-banner" class="section-p1">
		<div class="banner-box">
			<h4>crazy deals</h4>
			<h2>buy 1 get 1 free</h2>
			<span>The best classic dress is on sale at cara</span>
			<button class="white">Learn More</button>
		</div>
		<div class="banner-box ">
			<h4>spring/summer</h4>
			<h2>upcomming season</h2>
			<span>The best classic dress is on sale at cara</span>
			<button class="white">Learn More</button>
		</div>
	</section>
	<section>

		<section id="banner3" class="section-p1">
			<div class="banner-box">
				<h2>SEASONAL SALE</h2>
				<h3>Winter Collection -50% OFF</h3>
			</div>
			<div class="banner-box">
				<h2>NEW SAMRT PHONE COLLECTION</h2>
				<h3>Spring/Summer 2022</h3>
			</div>
			<div class="banner-box">
				<h2>SMART PHONE</h2>
				<h3>New Trendy Prints</h3>
			</div>
		</section>

		<jsp:include page="../common/footerWeb.jsp" />
		<jsp:include page="../common/footer.jsp" />
		<script src="<c:url value='/js/indexPage.js'/>"></script>
		<script src="<c:url value='/js/baseWeb.js'/>"></script>
</body>
</html>