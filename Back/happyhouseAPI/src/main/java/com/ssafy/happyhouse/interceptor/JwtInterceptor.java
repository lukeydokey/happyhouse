package com.ssafy.happyhouse.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.happyhouse.model.service.UserService;

@Component
public class JwtInterceptor implements HandlerInterceptor{
	private static final String HEADER_AUTH = "Autorization";
	
	@Autowired
	private UserService userService;
	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
////		final String token = request.getHeader(HEADER_AUTH).split(" ")[1];
////		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJzc2FmeTEyMyIsImlhdCI6MTY1Mjc5MjgzMCwiZXhwIjoxNjUyNzkzNDMwfQ.mjj9U4A-BeOfGOY6aNfZsq6HU4icyeLX6Zv4MmrC6v4";
//		if(token != null & userService.isUsable(token)) {
//			return true;
//		}else {
////			throw new Exception("유효하지 않은 토큰입니다.");
//			System.out.println("유효하지 않은 토큰입니다.");
//			return true;
//		}
//	}
//	
	
	
}
