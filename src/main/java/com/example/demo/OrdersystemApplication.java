package com.example.demo;

import com.example.demo.order.order.pojo.Order;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.example.**.mapper")
public class OrdersystemApplication /*extends SpringBootServletInitializer*/{
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(OrdersystemApplication.class);
//	}
	public static void main(String[] args) {
		SpringApplication.run(OrdersystemApplication.class, args);
	}

}
