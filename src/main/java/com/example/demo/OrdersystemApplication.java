package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.**.mapper")
public class OrdersystemApplication{
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(OrdersystemApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(OrdersystemApplication.class, args);
	}

}
