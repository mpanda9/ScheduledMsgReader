package com.mp.msg.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mp.msg.model.InputMessage;
import com.mp.msg.services.SchedulerService;

/**
 * controller file
 *
 */
@RestController
public class MessageSchedulerController {

	@Autowired
	SchedulerService schedulerService;

	@RequestMapping(value = "/msgScheduler", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<?> scheduleMsg(@RequestBody @Valid InputMessage input)
	{
		System.out.println("scheduler controller called..");
		String error = schedulerService.isValidRequest(input);
		if (error.length() > 0) {
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

		schedulerService.processMessage(input);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
