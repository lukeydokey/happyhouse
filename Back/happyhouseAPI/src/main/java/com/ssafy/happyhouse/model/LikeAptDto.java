package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "LikeAptDto (찜한 아파트 정보)", description = "찜한 아파트 정보를 가진   Domain Class")
public class LikeAptDto {

	@ApiModelProperty(value = "찜목록 ID")
	private String no;
	@ApiModelProperty(value = "사용자 ID")
	private String id;
	@ApiModelProperty(value = "아파트코 드")
	private String aptCode;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	@Override
	public String toString() {
		return "ParkDto2 [no=" + no + ", id=" + id + ", aptCode=" + aptCode + "]";
	}
	
	
}
