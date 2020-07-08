package com.mp.msg.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mp.msg.services.StartupService;

/**
 * This class will process the failed messages in case of restart
 *
 */
@Component
public class OnAppStartup {

	@Autowired
	StartupService startService;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {

		startService.process();

		System.out.println("Event on app startup");
	}
}
