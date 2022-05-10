package com.training.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.model.ResponseDataModel;
import com.training.service.IProductService;

@Controller
@RequestMapping(value = { "/" })
public class IndexController {
	@Autowired
	IProductService productService;
	@GetMapping
	public String initPage() {
		return "index";
	}

	@GetMapping(value = { "/home" })
	public String homePage() {
		return "indexPage";
	}
//	@GetMapping(value = { "/home" })
//	public String homePage() {
//		return "homePage";
//	}
	
	@GetMapping(value = {"login"})
	public String login() {
		return "login" ;
	}
	
	@PostMapping(value = { "api/search/{pageNumber}" })
	@ResponseBody
	public ResponseDataModel searchApi(@RequestBody Map<String, Object> searchConditions,
			@PathVariable("pageNumber") int pageNumber) {
		return productService.searchWithConditionsWeb(searchConditions, pageNumber);
	}
	
	@GetMapping(value = "api/getTop10NewProducts")
	@ResponseBody
	public ResponseDataModel getTop10NewProducts() {
		return productService.findTopNewProducts();
	}
	@GetMapping(value = "api/get10RandomProducts")
	@ResponseBody
	public ResponseDataModel get10RandomProducts() {
		return productService.getRandom10ProductsEntities();
	}
	
	@GetMapping(value = "shop")
	public String shopPage() {
		return "shop";
	}
	
	@GetMapping(value = { "productDetails"})
	public String productDetails() {
		return "productDetails";
	}
	@GetMapping(value = { "shoppingCart"})
	public String shoppingCart() {
		return "shoppingCart";
	}
	@GetMapping(value = { "productDetailsApi"})
	@ResponseBody
	public ResponseDataModel productDetailsApi(@RequestParam(name = "id") Long Id) {
		return productService.findByProductIdApi(Id);
	}

}