package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.jwt.JwtTokenProvider;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.mapper.UserMapper;

import io.jsonwebtoken.Claims;

@Service
public class UserServiceImpl implements UserService {
	

	@Autowired
	private UserMapper userMapper;

	private JwtTokenProvider jwtProvider = new JwtTokenProvider();
	
	@Override
	public int idCheck(String id) throws Exception {
		return userMapper.idCheck(id);
	}

	@Override
	public UserDto login(UserDto userDto) throws Exception {
		return userMapper.login(userDto);
	}

	@Override
	public UserDto getUser(String userId) throws Exception {
		return userMapper.getUser(userId);
	}

	@Override
	public void insertUser(UserDto userDto) throws Exception {
		userMapper.insertUser(userDto);
	}

	@Override
	public void updateUser(UserDto userDto) throws Exception {
		userMapper.updateUser(userDto);
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		userMapper.deleteUser(userId);
	}
	
	@Override
	public boolean isUsable(String jwt) throws Exception {
		if(jwt!=null) {
			if(jwtProvider.validateToken(jwt)) {
				Claims claim = jwtProvider.getInformation(jwt);
				System.out.println("유효한 토큰");
				System.out.println(claim.get("userId"));
				return true;
			}
		}
		System.out.println("유효하지 않은 토큰");
		return false;
	}
	
	
}