package com.jd;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringCloudApplication
@EnableEurekaClient
public class ClouldCommonApplication{

	public static void main(String[] args) {
		SpringApplication.run(ClouldCommonApplication.class, args);
		
	}
}
