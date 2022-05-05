package com.training.service;

import java.util.Map;

import com.training.entity.OrderDetailsEntity;
import com.training.model.ResponseDataModel;

public interface IOrderDetailsService {
ResponseDataModel addApi(OrderDetailsEntity orderEntity);
	
	ResponseDataModel updateApi(OrderDetailsEntity orderEntity);
	
	ResponseDataModel deteleApi(Long orderDetailsId);
	
}
