<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Orders Management</title>
<jsp:include page="../common/head.jsp" />
<link rel="stylesheet" href="<c:url value='/css/order.css'/>">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

</head>
<body>
<jsp:include page="../common/header.jsp" />
	<div class="container">
	<div class="sub-header">
			<div class="float-left sub-title">Orders Management</div>
			
		</div>
		<div class="container">
			<div class="row">
				<div class="col ">
					<label for="keyword"> Customer Name</label> <input type="text"
						class="form-control" id="keyword" name="keyword" />
				</div>
				<div class="col">
					<lable for="dateFrom">Date From</lable>
					<input type="date" class="form-control" id="dateFrom"
						name="dateFrom" />
				</div>
				<div class="col">
					<lable for="dateTo">Date To</lable>
					<input type="date" class="form-control" id="dateTo" name="dateTo" />
				</div>
			</div>
		</div>
		<table class="table table-bordered hover order-column"
			id="orderInfoTable">
			<thead>
				<tr class="text-center">
					<th scope="col"></th>
					<th scope="col">ID</th>
					<th scope="col">Customer Name</th>
					<th scope="col">Customer Email</th>
					<th scope="col">Customer Phone</th>
					<th scope="col">Customer Address</th>
					<th scope="col">Amount</th>
					<th scope="col">order status</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>

	<!-- Modal Edit Order -->
	<div class="modal fade" id="orderInfoModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<form id="orderInfoForm" role="form" enctype="multipart/form-data">
					<div class="modal-header">
						<h5 class="modal-title">Edit Order Status</h5>
						<button type="button" class="close" data-bs-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group d-none">
							<input type="hidden" class="form-control" name="orderId"
								id="orderId">
						</div>
						<div class="form-group">
							<label for="customerName">Customer Name <span
								class="required-mask">(*)</span></label> <input type="text"
								class="form-control" id="customerName" name="customerName" readonly>
						</div>
						<div class="form-group">
							<label for="customerAddress">Customer Address <span
								class="required-mask">(*)</span></label> <input type="text"
								class="form-control" id="customerAddress" name="customerAddress" readonly>
						</div>
						<div class="form-group">
							<label for="customerPhone">Amount </label> 
							<input type="text" class="form-control" id="amount" name="amount" readonly>
						</div>
						<div class="form-group">
							<label for="orderStatus">Amount </label> 
							<select type="text" class="form-control" id="orderStatus" name="orderStatus" >
								<option value = 0>Pendding</option>
								<option value = 1>Confirmed</option>
								<option value = 2>Cancelled</option>
							</select>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancel</button>
						<button type="button" class="btn btn-primary" id="saveOrderBtn">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Modal Confirm Deleting Cart -->

	<div class="modal fade" id="confirmDeleteModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Delete This Order </h5>
					<button type="button" class="close" data-bs-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>
						Do you want to delete <b id="deleteOrder"></b>?
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary#"
						data-bs-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="deleteSubmitBtn">Save</button>
				</div>
			</div>
		</div>
	</div>

<!-- Modal Shopping Cart -->
	<div class="modal fade" id="detailsOrderModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Order Details</h5>
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
							<div id="subtotal">
								<table>
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
				</div>
			</div>
		</div>
	</div>
	
<!-- Modal Order Successfully-->	
	
	
	
	<<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/order.js'/>"></script>




</body>
</html>