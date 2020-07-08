package com.mp.msg.services;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mp.msg.model.InputMessage;

/**
 * This class will call the REST service to schedule messages -- Unused class
 */
@Component
public class PushService {

	//@Scheduled(fixedDelay = 5000)
	//@Scheduled(fixedRate = 5000)  //Or use this
	public void demoServiceMethod()
	{
		final String uri = "http://localhost:8080/msgScheduler";
		InputMessage input = new InputMessage();
		input.setInputMessage("First message");
		LocalDateTime datetime= LocalDateTime.now();
		input.setScheduleTime(datetime.plusSeconds(4));

		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		ResponseEntity<InputMessage> result = restTemplate.postForObject(uri, input.getInputMessage(), ResponseEntity.class);

		System.out.println(result.getStatusCode().toString());
		System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());

	}
}
