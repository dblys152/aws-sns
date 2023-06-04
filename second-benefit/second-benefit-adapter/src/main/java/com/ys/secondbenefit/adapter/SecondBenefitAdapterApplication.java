package com.ys.secondbenefit.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ys.secondbenefit")
public class SecondBenefitAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondBenefitAdapterApplication.class, args);
	}

}
