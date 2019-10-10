package com.idosinchuk.teagile.core.service.common;

import lombok.Data;

@Data
public class CustomMessage {
	private int statusCode;
	private String message;

	public CustomMessage() {
		this.setMessage("Empty");
	}
}
