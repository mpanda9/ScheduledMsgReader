package com.mp.msg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan("com.mp.msg.entity")
public class ScheduledMsgReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduledMsgReaderApplication.class, args);
	}

}
