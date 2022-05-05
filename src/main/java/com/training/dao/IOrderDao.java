package com.training.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.training.entity.OrdersEntity;

@Repository
public interface IOrderDao  extends JpaRepository<OrdersEntity, Long>, JpaSpecificationExecutor<OrdersEntity>{
	
	OrdersEntity findByOrderId(Long orderId);
	
	List<OrdersEntity> findByCustomerName(String customerName);
	
}
