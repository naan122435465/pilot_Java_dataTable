package com.training.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.common.constant.Constants;
import com.training.dao.IOrderDetailsDao;
import com.training.entity.OrderDetailsEntity;
import com.training.model.ResponseDataModel;
import com.training.service.IOrderDetailsService;

public class OrderDetailsServiceImpl implements IOrderDetailsService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	IOrderDetailsDao orderDetailsDao;

	@Override
	public ResponseDataModel addApi(OrderDetailsEntity orderEntity) {
		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			orderDetailsDao.saveAndFlush(orderEntity);
			responseMsg = "Order Detail is add Successfully";
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = " Error when add Orders Detail";
			LOGGER.error("Error when update Orders Detail : ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel updateApi(OrderDetailsEntity orderEntity) {
		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			orderDetailsDao.saveAndFlush(orderEntity);
			responseMsg = "Order Detail is add Successfully";
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = " Error when add Orders Detail";
			LOGGER.error("Error when update Orders Detail : ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel deteleApi(Long orderDetailsId) {
		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		OrderDetailsEntity orderDetailsEntity = orderDetailsDao.findByOrderDetailsId(orderDetailsId);
		try {
			if(orderDetailsEntity != null) {
				orderDetailsDao.delete(orderDetailsEntity);
				responseMsg = "Order Details is deleted successfully";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = " Error when deleted Order Details ";
			LOGGER.error("Error when deleted Order Details  : ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

}
