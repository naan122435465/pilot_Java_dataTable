package com.training.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.entity.OrdersEntity;
import com.training.model.ResponseDataModel;
import com.training.service.IOrderService;

@Controller
@RequestMapping(value = {"/orders"})
public class OrderController {

	@Autowired
	IOrderService orderService;
	
	@GetMapping
	public String initPage(Model model) {
		return "order-index";
	}
	
	@PostMapping(value = {"/api/update"})
	@ResponseBody
	public ResponseDataModel updateApi(@RequestParam("id") long id, @RequestParam("orderStatus") int orderStatus) {
		return orderService.updateApi(id,orderStatus);
	}
	
	@DeleteMapping(value = {"/api/delete"})
	@ResponseBody
	public ResponseDataModel deleteApi(@RequestParam("id") long id) {
		return orderService.deteleApi(id);
	}
	
	@PostMapping(value = {"/api/orderList"})
	@ResponseBody
	public Map<String, Object> orderList(@RequestBody Map<String, Object> conditionMap){
		return orderService.searchWithConditions(conditionMap);
	}
	
	@GetMapping(value = {"/api/order"})
	@ResponseBody
	public ResponseDataModel getOrderById(@RequestParam("id") long id) {
		return orderService.findOrderById(id);
	}
}
