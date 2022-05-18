package com.ssafy.happyhouse.model;

public class UserDto {
	
	private String id;
	private String password;
	private String name;
	private String address;
	private String phonenumber;
	private String gender;
	
	
	
	public UserDto() {
		super();
	}

	public UserDto(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	public UserDto(String id, String password, String name, String address, String phonenumber, String gender) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
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
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
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
		return "UserDto [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address
				+ ", phonenumber=" + phonenumber + ", gender=" + gender + "]";
	}

	
	
}