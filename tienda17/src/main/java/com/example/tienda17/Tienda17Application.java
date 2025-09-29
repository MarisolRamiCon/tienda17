package com.example.tienda17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class Tienda17Application {

	public static void main(String[] args) {
		SpringApplication.run(Tienda17Application.class, args);
	}

}
