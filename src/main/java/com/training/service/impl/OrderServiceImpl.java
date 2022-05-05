package com.training.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.training.common.constant.Constants;
import com.training.dao.IOrderDao;
import com.training.dao.IOrderDetailsDao;
import com.training.dao.jpaspec.OrderJpaSpecitification;
import com.training.entity.OrdersEntity;
import com.training.model.ResponseDataModel;
import com.training.service.IOrderService;

public class OrderServiceImpl implements IOrderService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	IOrderDao orderDao;
	@Autowired
	IOrderDetailsDao orderDetailsDao;

	@Override
	public ResponseDataModel updateApi(OrdersEntity orderEntity) {
		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			orderDao.saveAndFlush(orderEntity);
			responseMsg = "Orders is update Successfully";
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = " Error when update Orders";
			LOGGER.error("Error when update Orders : ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel deteleApi(Long orderId) {
		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		OrdersEntity orderEntity = orderDao.findByOrderId(orderId);
		try {
			if (orderEntity != null) {
				orderDetailsDao.deleteByordersEntity(orderEntity);
				orderDao.delete(orderEntity);
				responseMsg = "Order is deleted successfully";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = " Error when deleted order";
			LOGGER.error("Error when deleted order : ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public Map<String, Object> searchWithConditions(Map<String, Object> conditionMap) {
		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap();
		int pageSize = (int) conditionMap.get("length");
		int pageNumber = (int) conditionMap.get("start");
		if (pageNumber != 0) {
			pageNumber = pageNumber / pageSize;
		}
		String sSortDir = (String) conditionMap.get("sSortDir");
		int iSortNum = (int) conditionMap.get("iSortNum");
		String iSortColumn = "orderId";
		switch (iSortNum) {
		case 0:
			break;
		case 1:
			iSortColumn = "amount";
			break;
		case 2:
			iSortColumn = "orderDate";
			break;
		case 3:
			iSortColumn = "customerAdress";
			break;
		case 4:
			iSortColumn = "customerName";
			break;
		}
		List<OrdersEntity> ordersEntities = orderDao.findAll(OrderJpaSpecitification.getSearchCriteria(conditionMap));
		try {
			Sort sortInfo = Sort.by(Sort.Direction.DESC, iSortColumn);
			if ("asc".equals(sSortDir)) {
				sortInfo = Sort.by(Sort.Direction.ASC, iSortColumn);
				Pageable pageable = PageRequest.of(pageNumber, pageSize, sortInfo);
				Page<OrdersEntity> orderEntityPage = orderDao
						.findAll(OrderJpaSpecitification.getSearchCriteria(conditionMap), pageable);
				responseMap.put("data", orderEntityPage.getContent());
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all Orders: ", e);
		}
		responseMap.put("responseCode", responseCode);
		responseMap.put("responseMsg", responseMsg);
		responseMap.put("recordsFiltered", ordersEntities.size());
		responseMap.put("recordsTotal", ordersEntities.size());
		responseMap.put("draw", (int) conditionMap.get("draw"));
		return responseMap;
	}

	@Override
	public ResponseDataModel findOrderById(Long orderId) {
		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap();
		try {
			OrdersEntity ordersEntity = orderDao.findByOrderId(orderId);
			responseMap.put("data", ordersEntity);
			responseMsg = "";
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = " Error when get Order Item";
			LOGGER.error("Error when get Order Item  : ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}
}
