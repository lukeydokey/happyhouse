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
	@ApiModelProperty(value = "아파트 이름")
	private String aptName;
	@ApiModelProperty(value = "찜목록 ID")
	private String dongCode;
	@ApiModelProperty(value = "사용자 ID")
	private String dongName;
	@ApiModelProperty(value = "아파트코 드")
	private String floor;
	@ApiModelProperty(value = "아파트 이름")
	private String gugunName;
	@ApiModelProperty(value = "찜목록 ID")
	private String jibun;
	@ApiModelProperty(value = "사용자 ID")
	private String lat;
	@ApiModelProperty(value = "아파트코 드")
	private String lng;
	@ApiModelProperty(value = "아파트 이름")
	private String sidoName;
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
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
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
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
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
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	@Override
	public String toString() {
		return "ParkDto2 [no=" + no + ", id=" + id + ", aptCode=" + aptCode + "]";
	}
	
	
}
