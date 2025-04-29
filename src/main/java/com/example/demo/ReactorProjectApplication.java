package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.r2dbc.R2dbcDataAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {
		R2dbcAutoConfiguration.class,
		R2dbcDataAutoConfiguration.class
})
public class ReactorProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactorProjectApplication.class, args);
	}

}
