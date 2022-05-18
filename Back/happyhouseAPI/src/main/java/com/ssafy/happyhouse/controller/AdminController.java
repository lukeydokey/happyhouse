package com.ssafy.happyhouse.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@Api("어드민 컨트롤러 V1")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	//마이페이지
	@RequestMapping(value = "/mypage/{uid}", method = RequestMethod.GET, headers = { "Content-type=application/json" })
	public ResponseEntity<?> userInfo(@PathVariable("uid") String userid, HttpSession session){
		
		try {
			UserDto userDto = userService.getUser(userid);
			if(userDto != null)
				return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	//회원 정보 수정
	//@PutMapping("/mypage")
	@RequestMapping(value = "/mypage", method = RequestMethod.PUT, headers = { "Content-type=application/json" })
	public ResponseEntity<?> modifyUser(@RequestBody UserDto userDto, HttpSession session){
		try {
			System.out.println(userDto);
			userService.updateUser(userDto);
			UserDto user= userService.getUser(userDto.getId());
			session.setAttribute("userinfo", user);
			return new ResponseEntity<UserDto>(user, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	//회원 탈퇴
	@DeleteMapping(value = "/user/{uid}")
	public ResponseEntity<?> userDelete(@PathVariable("uid") String userid, HttpSession session)  {
		try {
			userService.deleteUser(userid);
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
