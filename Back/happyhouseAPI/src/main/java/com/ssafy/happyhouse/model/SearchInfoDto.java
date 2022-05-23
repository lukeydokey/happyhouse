package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SearchInfo (검색 정보)", description = "검색 정보를 가진   Domain Class")
public class SearchInfoDto {
	@ApiModelProperty(value = "유저 아이디")
	private String id;
	@ApiModelProperty(value = "동 코드")
	private String dongCode;
	@ApiModelProperty(value = "동 이름")
	private String dongName;
	@ApiModelProperty(value = "구군 이름")
	private String gugunName;
	@ApiModelProperty(value = "시도 이름")
	private String sidoName;
	@ApiModelProperty(value = "검색횟수")
	private int hit;
	@ApiModelProperty(value = "성별")
	private String gender;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "SearchInfoDto [id=" + id + ", dongCode=" + dongCode + ", dongName=" + dongName + ", gugunName="
				+ gugunName + ", sidoName=" + sidoName + ", hit=" + hit + ", gender=" + gender + "]";
	}
	
	
	
}
