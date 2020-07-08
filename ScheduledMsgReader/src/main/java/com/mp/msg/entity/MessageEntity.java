package com.mp.msg.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class
 *
 */
@Entity(name = "MESSAGE_TABLE")
public class MessageEntity {

	@Id
	@Column(name = "messageId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer messageId;
	@Column(name = "input_message")
	String inputMessage;
	@Column(name="deliveryTime")
	LocalDateTime deliveryTime;
	@Column(name = "delivered")
	String delivered;

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		System.out.println("===>" + messageId);
		this.messageId = messageId;
	}
	public String getMessage() {
		return inputMessage;
	}
	public void setMessage(String message) {
		this.inputMessage = message;
	}
	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(LocalDateTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getDelivered() {
		return delivered;
	}
	public void setDelivered(String delivered) {
		this.delivered = delivered;
	}

	@Override
	public String toString() {
		return "MessageEntity [messageId=" + messageId + ", inputMessage=" + inputMessage + ", deliveryTime="
				+ deliveryTime + ", delivered=" + delivered + "]";
	}

}
