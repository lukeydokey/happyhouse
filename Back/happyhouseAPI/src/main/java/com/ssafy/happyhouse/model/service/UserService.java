package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.UserDto;

public interface UserService {
	int idCheck(String id) throws Exception;
	UserDto login(UserDto userDto) throws Exception;
	UserDto getUser(String userId) throws Exception;
	void insertUser(UserDto userDto) throws Exception;
	void updateUser(UserDto userDto) throws Exception;
	void deleteUser(String userId) throws Exception;
	boolean isUsable(String jwt) throws Exception;
}