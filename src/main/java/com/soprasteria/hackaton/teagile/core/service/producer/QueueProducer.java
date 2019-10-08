package com.soprasteria.hackaton.teagile.core.service.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soprasteria.hackaton.teagile.core.service.dto.MailDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QueueProducer {

	@Value("${fanout.exchange}")
	private String fanoutExchange;

	private final RabbitTemplate rabbitTemplate;

	@Autowired
	public QueueProducer(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	public void produceMail(MailDTO mailDTO) {

		try {
			log.info("Storing notification...");
			rabbitTemplate.setExchange(fanoutExchange);
			rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(mailDTO));
			log.info("Notification stored in queue sucessfully");
		} catch (Exception e) {
			return;
		}
	}
}