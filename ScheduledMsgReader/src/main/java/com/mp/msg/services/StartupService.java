package com.mp.msg.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mp.msg.dao.MessageDao;
import com.mp.msg.entity.MessageEntity;

@Service
public class StartupService {

	@Autowired
	MessageDao dao;

	@Autowired
	SchedulerService scheduler;

	/**
	 * schedule the future messages in case of restart
	 */
	public void process() {

		List<MessageEntity> msgList = dao.getAllMessagesNotProcessed();

		for (MessageEntity e : msgList) {
			long delay = ChronoUnit.SECONDS.between(LocalDateTime.now(), e.getDeliveryTime());
			if (delay <= 0) {
				printFailed(e);
			} else {
				scheduler.scheduleMsg(e);
			}
		}

	}

	/**
	 * print the past messages failed to print
	 *
	 * @param entity
	 */
	public void printFailed(MessageEntity entity) {
		System.out.println("Printing failed task : " + entity.getMessage() + " : " + entity.getDeliveryTime());
	}
}
