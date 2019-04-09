package com.jd.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jd.Vo.UserVo;
import com.jd.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public UserVo findByUsername(String username) {
		
		log.info("没有调用.....");
		
		return null;
	}   

}
