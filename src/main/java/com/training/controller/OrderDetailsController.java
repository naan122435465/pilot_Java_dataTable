package com.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.model.ResponseDataModel;
import com.training.service.IOrderDetailsService;

@Controller
@RequestMapping(value = {"/orderDetails"})
public class OrderDetailsController {

	@Autowired
	IOrderDetailsService orderDetailsService;
	
	@GetMapping(value = {"/api/orderDetail"})
	@ResponseBody
	public ResponseDataModel getOrderDetailById(@RequestParam("id") long id) {
		return orderDetailsService.getOrderDetailsByOrderId(id);
	}
	@GetMapping(value = {"/api/orderDetailList"})
	@ResponseBody
	public ResponseDataModel getOrderDetailByOrderId(@RequestParam("id") long id) {
		return orderDetailsService.getOrderDetailsByOrderId(id);
	}
}
