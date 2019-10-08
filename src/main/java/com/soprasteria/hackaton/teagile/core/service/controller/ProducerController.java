package com.soprasteria.hackaton.teagile.core.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.hackaton.teagile.core.service.dto.MailDTO;
import com.soprasteria.hackaton.teagile.core.service.producer.QueueProducer;

@RestController
@RequestMapping(value = "/api/v1")
public class ProducerController {

	QueueProducer queueProducer;

	@Autowired
	public ProducerController(QueueProducer queueProducer) {
		this.queueProducer = queueProducer;
	}

	@PostMapping(path = "/rabbitmq/produce/mail", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void sendMail(@RequestBody MailDTO mailDTO) {

		queueProducer.produceMail(mailDTO);
	}
}
