package com.le.garbage_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GarbageManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GarbageManagerApplication.class, args);
	}

}
