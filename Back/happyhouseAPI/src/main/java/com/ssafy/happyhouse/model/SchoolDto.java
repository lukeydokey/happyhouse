package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SchoolDto (학교 정보)", description = "학교 정보를 가진   Domain Class")
public class SchoolDto {

	@ApiModelProperty(value = "학교 ID")
	private String id;
	@ApiModelProperty(value = "학교 이름")
	private String name;
	@ApiModelProperty(value = "학교 분류")
	private String level;
	@ApiModelProperty(value = "주소")
	private String address;
	@ApiModelProperty(value = "위도")
	private String lat;
	@ApiModelProperty(value = "경도")
	private String lng;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "SchoolDto [id=" + id + ", name=" + name + ", level=" + level + ", address=" + address + ", lat=" + lat
				+ ", lng=" + lng + "]";
	}
	
}
