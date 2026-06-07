package com.vocallabs.outreach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AutomatedOutreachPipelineApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomatedOutreachPipelineApplication.class, args);
	}

}
