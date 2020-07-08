package com.mp.msg.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * model class for request
 *
 */
public class InputMessage {

	String inputMessage;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	LocalDateTime deliveryTime;

	public String getInputMessage() {
		return inputMessage;
	}
	public void setInputMessage(String inputMessage) {
		this.inputMessage = inputMessage;
	}
	public LocalDateTime getScheduleTime() {
		return deliveryTime;
	}
	public void setScheduleTime(LocalDateTime localDateTime) {
		this.deliveryTime = localDateTime;
	}

	public void validate(LocalDateTime deliveryTime) {
		deliveryTime.isBefore(LocalDateTime.now());
	}
}
