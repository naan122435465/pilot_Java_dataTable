package com.training.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.common.constant.Constants;
import com.training.dao.IOrderDao;
import com.training.dao.IOrderDetailsDao;
import com.training.dao.IProductDao;
import com.training.entity.OrderDetailsEntity;
import com.training.entity.OrdersEntity;
import com.training.entity.ProductEntity;
import com.training.model.CartModel;
import com.training.model.ResponseDataModel;
import com.training.service.IShoppingCartService;

import net.bytebuddy.asm.Advice.This;

@Service
@Transactional
public class ShoppingCartServiceImpl implements IShoppingCartService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private Map<ProductEntity, Integer> carts = new HashMap();
	@Autowired
	IOrderDao orderDao;
	@Autowired
	IOrderDetailsDao orderDetailsDao;
	@Autowired
	IProductDao productDao;

	@Override
	public ResponseDataModel addProductInCart(long id, int quantity) {
		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		//Map<String, Object> responseMap = new HashMap<>();
	//	List<CartModel> cartList = new ArrayList();
		try {
			ProductEntity productEntity = productDao.findByProductId(id);
			if (productEntity != null) {
				if (carts.containsKey(productEntity)) {
					carts.replace(productEntity, carts.get(productEntity) + quantity);
				} else {
					carts.put(productEntity, quantity);
				}

			}
			// responseMap.put("carts", carts);
			responseCode = Constants.RESULT_CD_SUCCESS;
			responseMsg = "Adding product in cart successfully";

		} catch (Exception e) {
			responseMsg = " Error when adding product in cart ";
			LOGGER.error("Error when adding product in cart  : ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel updateProductInCart(long id, int quantity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		//Map<String, Object> responseMap = new HashMap<>();
		//List<CartModel> cartList = new ArrayList();
		try {
			ProductEntity productEntity = productDao.findByProductId(id);
			if (productEntity != null) {
				if (carts.containsKey(productEntity)) {
					if (quantity != 0) {
						carts.replace(productEntity, quantity);
					} else {
						carts.remove(productEntity);
					}
				} else {
					carts.put(productEntity, quantity);
				}

			}
			// responseMap.put("carts", carts);
			responseCode = Constants.RESULT_CD_SUCCESS;
			responseMsg = "Adding product in cart successfully";

		} catch (Exception e) {
			responseMsg = " Error when adding product in cart ";
			LOGGER.error("Error when adding product in cart  : ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel removeProductInCart(long id) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		ProductEntity productEntity = productDao.findByProductId(id);
		Map<String, Object> responseMap = new HashMap<>();
		try {
			carts.remove(productEntity);
			responseMap.put("carts", carts);
			responseCode = Constants.RESULT_CD_SUCCESS;
			responseMsg = "Removing product in cart successfully";
		} catch (Exception e) {
			responseMsg = " Error when remove product in cart ";
			LOGGER.error("Error remove product in cart : ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}

//	@Override
//	public List<CartModel> getProductsInCart() {
//		// TODO Auto-generated method stub
//		List<CartModel> cartList = new ArrayList();
//		
//;		for(Map.Entry<ProductEntity, Integer> cart : carts.entrySet()) {
//			CartModel cartModel = new CartModel(cart.getKey(),cart.getValue());
//		}
//		return cartList;
//	}

	@Override
	public ResponseDataModel checkout(Map<ProductEntity, Integer> carts, OrdersEntity orderEntity) {
		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		List<OrderDetailsEntity> orderDetailsSet = new ArrayList();
		try {			
			orderEntity.setOrderDate(Date.valueOf(LocalDate.now()));
			orderDao.saveAndFlush(orderEntity);
			for (Map.Entry<ProductEntity, Integer> cart : carts.entrySet()) {
				ProductEntity productInCart = cart.getKey();
				int quantity = cart.getValue();
				double price = productInCart.getPrice();
				double amount = price * quantity;
				OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
				orderDetailsEntity.setAmount(amount);
				orderDetailsEntity.setOrdersEntity(orderEntity);
				orderDetailsEntity.setPrice(price);
				orderDetailsEntity.setQuanity(quantity);
				orderDetailsEntity.setProductEntity(productInCart);
				orderDetailsSet.add(orderDetailsEntity);
				
				//orderDetailsDao.saveAndFlush(orderDetailsEntity);				
			}
			orderDetailsDao.saveAll(orderDetailsSet);
			orderDetailsDao.flush();
			this.carts.clear();
			responseCode = Constants.RESULT_CD_SUCCESS;
			responseMsg = "Order is successfully";
			responseMap.put("orderDetails", orderDetailsSet);
			responseMap.put("orders", orderEntity);
			
		} catch (Exception e) {
			responseMsg = " Error when create Order ";
			LOGGER.error("Error when create Order : ", e);
		}
		
		return new ResponseDataModel(responseCode, responseMsg,responseMap);
	}

	@Override
	public Map<ProductEntity, Integer> getProductsInCart() {

		return Collections.unmodifiableMap(carts);
	}

	public ResponseDataModel convertToCartModels(Map<ProductEntity, Integer> carts) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		List<CartModel> cartList = new ArrayList();
		Map<String, Object> responseMap = new HashMap<>();
		try {
			for (Map.Entry<ProductEntity, Integer> cart : carts.entrySet()) {
				CartModel cartModel = new CartModel(cart.getKey(), cart.getValue());
				cartList.add(cartModel);
			}
			responseCode = Constants.RESULT_CD_SUCCESS;
			responseMap.put("cart", cartList);
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all Product in Cart: ", e);
		}

		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}

}
