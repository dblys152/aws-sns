package com.ys.firstbenefit.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = "com.ys.firstbenefit")
@EnableFeignClients
@Import({FeignAutoConfiguration.class})
public class FirstBenefitAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstBenefitAdapterApplication.class, args);
	}

}
