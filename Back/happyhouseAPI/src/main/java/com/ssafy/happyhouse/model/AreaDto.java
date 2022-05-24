package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AreaDto (주변 시설 정보)", description = "주변 시설 정보를 가진   Domain Class")
public class AreaDto {

	@ApiModelProperty(value = "시설 ID")
	private int no;
	@ApiModelProperty(value = "시설 타입")
	private String type;
	@ApiModelProperty(value = "시설 이름")
	private String name;
	@ApiModelProperty(value = "시설 세부 분류")
	private String level;
	@ApiModelProperty(value = "주소")
	private String address;
	@ApiModelProperty(value = "위도")
	private String lat;
	@ApiModelProperty(value = "경도")
	private String lng;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
		return "AreaDto [no=" + no + ", type=" + type + ", name=" + name + ", level=" + level + ", address=" + address
				+ ", lat=" + lat + ", lng=" + lng + "]";
	}
	
}
