package com.jd;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
public class ClouldCommonApplication{

	public static void main(String[] args) {
		SpringApplication.run(ClouldCommonApplication.class, args);
		
		
//		$2a$10$kByiCa.bGGLYRGVXh7lQCew7sJnbVa3rRGzVqYBSnTMketAGDZCsi
//		System.out.println(new BCryptPasswordEncoder().encode("1234"));;
//		
//		System.out.println(new BCryptPasswordEncoder().matches("1234", "$2a$10$nGCzXbrZdPI8k5i/qR7j3.1edT3Omqm1dnLuZWiT5bd7l5cgitzxq"));;
		
	}
	
	
}
	