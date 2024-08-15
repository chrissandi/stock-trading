package com.apps.ohlc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OhlcApplication {

	public static void main(String[] args) {
		SpringApplication.run(OhlcApplication.class, args);
	}

}
