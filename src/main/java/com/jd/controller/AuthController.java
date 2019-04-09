package com.jd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authapi")
public class AuthController {
	

	@Autowired
    private TokenStore tokenStore;

    @PostMapping("/auth")
    public Object auth(@RequestHeader("Authorization") String auth){
    	
    	System.out.println(auth);
    	
        return  tokenStore.readAuthentication(auth.split(" ")[1]).getPrincipal();
    }
    
    
    @RequestMapping("/test")
    public Object test(){
    	
        return "test";
    }

}