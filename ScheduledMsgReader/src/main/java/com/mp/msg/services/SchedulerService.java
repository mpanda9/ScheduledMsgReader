package com.mp.msg.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mp.msg.dao.MessageDao;
import com.mp.msg.entity.MessageEntity;
import com.mp.msg.model.InputMessage;

/**
 * This class will validate and process the data from request
 *
 */
@Service
public class SchedulerService {

	@Autowired
	MessageDao dao;

	/**
	 * save the data into database and schedule the messages. first save the data to
	 * generate the id for mapping
	 */
	public void processMessage(InputMessage msg) {
		MessageEntity entity = saveMessage(msg);
		scheduleMsg(entity);
	}

	/**
	 * save message in DB
	 *
	 * @param msg
	 * @return
	 */
	public MessageEntity saveMessage(InputMessage msg) {
		MessageEntity me = new MessageEntity();
		me.setMessage(msg.getInputMessage());
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime lt = LocalDateTime.parse(msg.getScheduleTime().toString(), formatter);
		me.setDeliveryTime(lt);
		me.setDelivered("N");
		MessageEntity e = dao.save(me);
		return e;
	}

	/**
	 * schedule the message to print and run the print task
	 *
	 * @param entity
	 */
	public void scheduleMsg(MessageEntity entity)
	{
		Runnable task = () -> {
			System.out.println("Printing scheduled task : " + entity.getMessage() + " : " + entity.getDeliveryTime());
			entity.setDelivered("Y");
			//			MessageEntity updated =
			dao.save(entity);
			//			System.out.println(updated.getMessageId() + " : " + updated.getDelivered());

		};

		long delay = ChronoUnit.SECONDS.between(LocalDateTime.now(), entity.getDeliveryTime());
		System.out.println("delay - " +delay);
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
		scheduler.schedule(task, delay, TimeUnit.SECONDS);
	}

	/**
	 * process the failed messages at startup
	 *
	 * @param msg
	 */
	public void processFailedMsgs(InputMessage msg)
	{
		System.out.println("processing failed messages");
		Runnable task = () -> System.out
				.println("printing failed msg : " + msg.getInputMessage() + " : " + msg.getScheduleTime());

		ExecutorService es = Executors.newFixedThreadPool(5);

		es.execute(task);

	}

	/**
	 * validate the request
	 *
	 * @param input
	 * @return error messages if any
	 */
	public String isValidRequest(InputMessage input) {

		StringBuilder error = new StringBuilder();
		long delay = ChronoUnit.SECONDS.between(LocalDateTime.now(), input.getScheduleTime());
		if (StringUtils.isEmpty(input.getInputMessage())) {
			error.append("Invalid Message.");
		}
		boolean valid = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setLenient(false);

		try {
			format.parse(input.getScheduleTime().toString());
		} catch (Exception pe) {
			pe.printStackTrace();
			valid = false;
		}
		if (!valid) {
			error.append("Invalid date format. valid date formar is : yyyy-MM-dd HH:mm:ss");
		}
		if ((delay < 0)) {
			error.append("Only future date and time allowed.");
		}

		return error.toString();

	}
}
