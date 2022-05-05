package com.training.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.common.util.CommonUtil;

@Table(name = "ORDERS")
@Entity
public class OrdersEntity {

	@Id
	@Column(name = "Order_Id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	@Column(name = "AMOUNT", nullable = false)
	private double amount;
	
	@Column(name = "CUSTOMER_ADDRESS", nullable = false)
	private String customerAddress;

	@Column(name = "CUSTOMER_EMAIL", nullable = false)
	private String customerEmail;

	@Column(name = "CUSTOMER_NAME", nullable = false)
	private String customerName;

	@Column(name = "CUSTOMER_PHONE", nullable = false)
	private String customerPhone;

	@Column(name = "ORDER_DATE", nullable = false)
	private Date orderDate;
	
	@Transient
	private String saleDateFormat;

	@JsonIgnore
	@OneToMany(mappedBy = "ordersEntity", fetch = FetchType.LAZY)
	private Set<OrderDetailsEntity> orderDetailsSet;
	
	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getCustomerAddress() {
		return customerAddress;
	}


	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}


	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerPhone() {
		return customerPhone;
	}


	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public String getSaleDateFormat() {
		return CommonUtil.cvDateToString(orderDate, "dd-MM-yyyy");
	}


	public Set<OrderDetailsEntity> getOrderDetailsSet() {
		return orderDetailsSet;
	}


	public void setOrderDetailsSet(Set<OrderDetailsEntity> orderDetailsSet) {
		this.orderDetailsSet = orderDetailsSet;
	};

}
