package com.training.service;

import java.util.List;

import javax.mail.MessagingException;

import com.training.entity.OrderDetailsEntity;
import com.training.entity.OrdersEntity;

public interface MailService {
	void sendHtmlMail(List<OrderDetailsEntity> orderDetailsEntity ,OrdersEntity orderEntity) throws MessagingException;

}
