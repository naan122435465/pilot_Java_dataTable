package com.training.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.training.entity.OrderDetailsEntity;
import com.training.entity.OrdersEntity;
@Repository
public interface IOrderDetailsDao extends JpaRepository<OrderDetailsEntity, Long>, JpaSpecificationExecutor<OrderDetailsEntity>{
	
	OrderDetailsEntity findByOrderDetailsId(long orderDetailsId);
	void deleteByordersEntity(OrdersEntity ordersEntity);
}
