package com.wuy.rpc.user.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.wuy.rpc.netty.handler.param.Response;
import com.wuy.rpc.netty.util.ResponseUtil;
import com.wuy.rpc.user.bean.User;

@Controller
public class UserConroller {
	
	public Response save(User user){
		return ResponseUtil.createSuccessResult(user);
	}
	
	public Response saves(List<User> users){
		return ResponseUtil.createSuccessResult(users);
	}
	
	public Response add(int a,float b){
		return ResponseUtil.createSuccessResult(a+": a , b: "+b);
	}
}
