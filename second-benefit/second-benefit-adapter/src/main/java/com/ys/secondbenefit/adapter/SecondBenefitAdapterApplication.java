package com.ys.secondbenefit.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = "com.ys.secondbenefit")
@EnableFeignClients
@Import({FeignAutoConfiguration.class})
public class SecondBenefitAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondBenefitAdapterApplication.class, args);
	}

}
