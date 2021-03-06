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
<link rel="stylesheet" href="<c:url value='/css/shoppingCart.css'/>">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

</head>
<body>
	<jsp:include page="../common/headerWeb.jsp" />
	<section class="section-p1" id="cartModal">
		<table>
			<thead>
				<tr>
					<td>Remove</td>
					<td>Image</td>
					<td>Product</td>
					<td>Price</td>
					<td>Quantity</td>
					<td>Subtotal</td>
				</tr>
			</thead>
			<tbody id="finalCart">
				<tr>
					<td><i class="fa-light fa-trash-can"></i></td>
					<td><img src="image/product1.jpg" alt=""></td>
					<td>Iphone 14</td>
					<td>$1500</td>
					<td><input type="number" value="1" min="1"></td>
					<td>$1500</td>
				</tr>
				<tr>
					<td><i class="fa-light fa-xmark"></i></td>
					<td><img src="image/product1.jpg" alt=""></td>
					<td>Iphone 14</td>
					<td>$1500</td>
					<td><input type="number" value="1" min="1"></td>
					<td>$1500</td>
				</tr>
				<tr>
					<td><i class="fa-light fa-xmark"></i></td>
					<td><img src="image/product1.jpg" alt=""></td>
					<td>Iphone 14</td>
					<td>$1500</td>
					<td><input type="number" value="1" min="1"></td>
					<td>$1500</td>
				</tr>
			</tbody>
		</table>
		<section id="cart-add">
			<div id="information">
				<h3>Customer Information Form</h3>
				<p>Please fill out the customer information form to continue your
					order.</p>
				<form role="form" id="customerInfo">
					<table>
						<tr>
							<td><label for="customerName">Your full name :<span
									class="required-mask">(*)</span>
							</label></td>
							<td><input type="text" name="customerName" id="customerName">
							</td>
						</tr>
						<tr>
							<td><label for="customerPhone"> Your Phone Number :<span
									class="required-mask">(*)</span>
							</label></td>
							<td><input type="tel" name="customerPhone"
								id="customerPhone"></td>
						</tr>
						<tr>
							<td><label for="customerEmail">Your email :<span
									class="required-mask">(*)</span>
							</label></td>
							<td><input type="email" name="customerEmail"
								id="customerEmail"></td>
						</tr>
						<tr>
							<td><label for="customerAdress">Your Address :<span
									class="required-mask">(*)</span>
							</label></td>
							<td><input type="text" name="customerAddress"
								id="customerAddress"></td>
						</tr>



					</table>
				</form>

			</div>
			<div id="subtotalShopping">
				<h3>Cart Total</h3>
				<table>
					<tr>
						<td>Cart</td>
						<td id="finalTotal1">$34566</td>
					</tr>
					<tr>
						<td>Shiping</td>
						<td>Free</td>
					</tr>
					<tr>
						<td><strong>Total</strong></td>
						<td><Strong id="finalTotal2">$3456</Strong></td>
					</tr>
				</table>
				<button class="normal" type="submit" id="addOrder">Proceed
					to checkout</button>
			</div>
		</section>
		<hr>
	</section>
	<!-- Modal Order Successfully-->
	<div class="modal fade" id="orderSuccessModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Order Successfully</h5>
					<button type="button" class="close" data-bs-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<section>
						<table>
							<tr>
								<td><label for="customerName">Your full name : </span>
								</label></td>
								<td><input type="text" 
									id="customerNameOrderSuccess" readonly></td>
							</tr>
							<tr>
								<td><label for="customerPhoneOrderSuccess"> Your Phone Number :</span>
								</label></td>
								<td><input type="tel" 
									id="customerPhoneOrderSuccess"readonly></td>
							</tr>
							<tr>
								<td><label for="customerEmailOrderSuccess">Your email :</span>
								</label></td>
								<td><input type="email" 
									id="customerEmailOrderSuccess" readonly></td>
							</tr>
							<tr>
								<td><label for="customerAddressOrderSuccess">Your Address :</span>
								</label></td>
								<td><input type="text" 
									id="customerAddressOrderSuccess" readonly></td>
							</tr>
							<tr>
								<td><label for="orderDateOrderSuccess">Order Date :</span>
								</label></td>
								<td><input type="text" 
									id="orderDateOrderSuccess" readonly></td>
							</tr> 
						</table>
					</section>
					<section id="orderSuccess">
						<table>
							<thead>
								<tr>
									<td>Remove</td>
									<td colspan="2">Product</td>
									<td>Price</td>
									<td>Quantity</td>
									<td>Subtotal</td>
								</tr>
							</thead>
							<tbody id="cartRowOrderSuccess">
							</tbody>
						</table>
						<section id="cart-add">
							<div id="subtotal">
								<table>
									<tr>
										<td><strong>Total</strong></td>
										<td><Strong id="totalOrderSuccess">$3456</Strong></td>
									</tr>
								</table>

							</div>
						</section>
					</section>
				</div>
				<div class="modal-footer">
					<a type="button" class="btn btn-primary" id="saveEditBtn"
						href="http://localhost:8880/home"> Continute Shopping</a>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footerWeb.jsp" />
	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/shoppingCart.js'/>"></script>
	<script src="<c:url value='/js/baseWeb.js'/>"></script>
</body>
</html>