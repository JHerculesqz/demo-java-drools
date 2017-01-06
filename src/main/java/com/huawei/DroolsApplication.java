package com.huawei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DroolsApplication {
	// #region main

	public static void main(String[] args) {
		SpringApplication.run(DroolsApplication.class, args);
	}

	// #endregion
}
