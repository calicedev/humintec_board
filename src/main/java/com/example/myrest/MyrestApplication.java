package com.example.myrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyrestApplication.class, args);
	}

}
