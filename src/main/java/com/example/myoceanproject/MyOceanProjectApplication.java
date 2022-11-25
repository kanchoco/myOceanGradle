package com.example.myoceanproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyOceanProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyOceanProjectApplication.class, args);
	}

}
