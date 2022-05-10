<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<section id="header">
		<h2 class="logo">PILOT</h2>
		<div>
			<ul id="navbar">
				<li><a  href="http://localhost:8880/home">Home</a></li>
				<li><a href="http://localhost:8880/shop">Shop</a></li>
				<li><a href="#">Blog</a></li>
				<li><a href="#">About</a></li>
				<li><a href="#">Contact</a></li>
				<li id="lg-bag"><a href="#"><i
						class="fa-solid fa-basket-shopping"></i></a></li>
				<a href="#" id="close"><i class="fa fa-times"></i></a>
			</ul>
		</div>
		<div id="mobile">
			<a href="#"><i class="fa-solid fa-basket-shopping"></i></a> <i
				id="bar" class="fas fa-outdent"></i>
		</div>
	</section>
	<!-- Modal Shopping Cart -->
	<div class="modal fade" id="shoppingCart">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Shopping Cart</h5>
					<button type="button" class="close" data-bs-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<section  id="cartModal">
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
							<tbody id = "cartRow">
								
								
							</tbody>
						</table>
						<section id="cart-add">
							<div id="coupon">
								

							</div>
							<div id="subtotal">
								<h3>Cart Total</h3>
								<table>
									<tr>
										<td>Cart</td>
										<td id = "total">$34566</td>
									</tr>
									<tr>
										<td>Shiping</td>
										<td>Free</td>
									</tr>
									<tr>
										<td><strong>Total</strong></td>
										<td><Strong  id = "totalFinal">$3456</Strong></td>
									</tr>
								</table>
					
							</div>
						</section>
					</section>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary#"
						data-bs-dismiss="modal">Cancel</button>
					<a type="button" class="btn btn-primary" id="checkOut" href="http://localhost:8880/shoppingCart">Check
						Out</a>
				</div>
			</div>
		</div>
	</div>

	
