package com.training.service.impl;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.common.constant.Constants;
import com.training.entity.OrderDetailsEntity;
import com.training.entity.OrdersEntity;
import com.training.service.MailService;

@Service
@Transactional
public class MailServiceImpl implements MailService{
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendHtmlMail(List<OrderDetailsEntity> orderDetailsEntity ,OrdersEntity orderEntity) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
		helper.setFrom(Constants.MAIL_FROM);
		helper.setSubject("Order");
		helper.setTo(Constants.MAIL_TO);
		String html = "<p> Your have a order from: "+ orderEntity.getCustomerName()+" </p></br>"+
				"<p> Customer Address: "+ orderEntity.getCustomerAddress()+" </p></br>"+
				"<p> Customer Phone: "+ orderEntity.getCustomerPhone()+" </p></br>"+
				"<p> Please check order details on Admin Apllication </p></br>";					
		message.setContent(html,"text/html");
		helper.setText(html);
		mailSender.send(message);

	}


}
