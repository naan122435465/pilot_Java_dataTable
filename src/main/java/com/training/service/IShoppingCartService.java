package com.training.service;

import java.util.List;
import java.util.Map;

import com.training.entity.OrdersEntity;
import com.training.entity.ProductEntity;
import com.training.model.CartModel;
import com.training.model.ResponseDataModel;

public interface IShoppingCartService {

	ResponseDataModel addProductInCart(long id, int quantity);

	ResponseDataModel updateProductInCart(long id, int quantity);
	
	ResponseDataModel removeProductInCart(long id);

	Map<ProductEntity, Integer> getProductsInCart();

	ResponseDataModel checkout(Map<ProductEntity, Integer> carts, OrdersEntity orderEntity);

	ResponseDataModel convertToCartModels(Map<ProductEntity, Integer> carts);
}
