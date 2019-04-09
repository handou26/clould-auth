package com.jd.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jd.Vo.UserVo;
import com.jd.service.impl.UserServiceImpl;

@FeignClient(value="baseService",fallback=UserServiceImpl.class)
public interface UserService {
	
	@RequestMapping(value = "/sysuser/getdetail",method=RequestMethod.GET)
	UserVo findByUsername(@RequestParam("username") String username);
}