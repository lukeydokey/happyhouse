package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.UserDto;

@Mapper
public interface UserMapper {
	int idCheck(String id) throws SQLException;
	UserDto login(UserDto userDto) throws SQLException;
	UserDto getUser(String id) throws SQLException;
	void insertUser(UserDto userDto) throws SQLException;
	void updateUser(UserDto userDto) throws SQLException;
	void deleteUser(String id) throws SQLException;
	public UserDto userInfo(String id) throws SQLException;
	
}