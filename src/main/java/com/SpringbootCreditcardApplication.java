package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootCreditcardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCreditcardApplication.class, args);
	}

}
