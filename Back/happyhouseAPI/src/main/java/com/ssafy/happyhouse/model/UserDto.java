package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User (유저)", description = "id, 비밀번호, 이름, 이메일, 전화번호, 성별을 가진   Domain Class")
public class UserDto {

	@ApiModelProperty(value = "아이디")
	private String id;
	@ApiModelProperty(value = "비밀번호")
	private String password;
	@ApiModelProperty(value = "이름")
	private String name;
	@ApiModelProperty(value = "이메일")
	private String email;
	@ApiModelProperty(value = "전화번호")
	private String phonenumber;
	@ApiModelProperty(value = "성별")
	private String gender;
	
	
	
	public UserDto() {
		super();
	}

	public UserDto(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	public UserDto(String id, String password, String name, String email, String phonenumber, String gender) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
		this.gender = gender;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhonenumber() {
		return phonenumber;
	}
	
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", phonenumber=" + phonenumber + ", gender=" + gender + "]";
	}

	
	
}