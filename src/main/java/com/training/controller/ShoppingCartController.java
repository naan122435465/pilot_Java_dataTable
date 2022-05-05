package com.training.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.training.entity.OrdersEntity;
import com.training.entity.ProductEntity;
import com.training.model.CartModel;
import com.training.model.ResponseDataModel;
import com.training.service.IShoppingCartService;

@Controller
@RequestMapping(value = { "/cart" })
@SessionAttributes("shoppingCart")
public class ShoppingCartController {

	@Autowired
	IShoppingCartService shoppingCartService;

	@ModelAttribute("shoppingCart")
	public void setUpCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("shoppingCart", shoppingCartService.getProductsInCart());
	}

	@GetMapping("/api/shoppingcart")
	@ResponseBody
	public ResponseDataModel getCart(@ModelAttribute("shoppingCart") Map<ProductEntity, Integer> carts,
			HttpSession request) {

		return shoppingCartService.convertToCartModels(carts);
	}

	@GetMapping("/api/add")
	@ResponseBody
	public ResponseDataModel addCart(@RequestParam("id") long id , @RequestParam("quantity") int quantity) {
		return shoppingCartService.addProductInCart(id, quantity);
	}

	@GetMapping("/api/update")
	@ResponseBody
	public ResponseDataModel updateCart(@RequestParam("id") long id , @RequestParam("quantity") int quantity) {
		return shoppingCartService.updateProductInCart(id, quantity);
	}
	@GetMapping("/api/delete")
	@ResponseBody
	public ResponseDataModel removeCart(@RequestParam("id") long id) {

		return shoppingCartService.removeProductInCart(id);
	}
	
	@PostMapping("/api/addOrder")
	@ResponseBody
	public ResponseDataModel addOrder(@ModelAttribute("shoppingCart") Map<ProductEntity, Integer> carts,
			HttpSession request, @ModelAttribute OrdersEntity orderEntity) {

		return shoppingCartService.checkout(carts, orderEntity);
	}
}
