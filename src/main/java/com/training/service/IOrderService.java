package com.training.service;

import java.util.List;
import java.util.Map;

import com.training.entity.OrdersEntity;
import com.training.model.ResponseDataModel;

public interface IOrderService {

	
	ResponseDataModel updateApi(long id,int orderStatus);
	
	ResponseDataModel deteleApi(Long orderId);
	
	Map<String, Object> searchWithConditions(Map<String, Object> conditionMap);
	
	ResponseDataModel findOrderById(Long orderId);
}
