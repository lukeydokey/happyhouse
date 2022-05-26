package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HouseInfo (아파트 정보)", description = "아파트 정보를 가진   Domain Class")
public class HouseInfoDto {

	@ApiModelProperty(value = "아파트 코드")
	private long aptCode;
	@ApiModelProperty(value = "아파트 이름")
	private String aptName;
	@ApiModelProperty(value = "동 코드")
	private String dongCode;
	@ApiModelProperty(value = "동 이름")
	private String dongName;
	@ApiModelProperty(value = "시도 이름")
	private String sidoName;
	@ApiModelProperty(value = "구군 이름")
	private String gugunName;
	@ApiModelProperty(value = "건축 연도")
	private int buildYear;
	@ApiModelProperty(value = "지번 주소")
	private String jibun; 
	@ApiModelProperty(value = "도로명 주소")
	private String road;
	@ApiModelProperty(value = "위도")
	private String lat;
	@ApiModelProperty(value = "경도")
	private String lng;
	@ApiModelProperty(value = "이미지")
	private String img;
	@ApiModelProperty(value = "최근 가격")
	private String recentPrice;
	@ApiModelProperty(value = "거래 년도")
	private String dealYear;
	@ApiModelProperty(value = "거래 월")
	private String dealMonth;
	@ApiModelProperty(value = "거래 일자")
	private String dealDay;
	@ApiModelProperty(value = "면적")
	private String area;
	@ApiModelProperty(value = "층")
	private String floor;

	public long getAptCode() {
		return aptCode;
	}

	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
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

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	
	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getRecentPrice() {
		return recentPrice;
	}

	public void setRecentPrice(String recentPrice) {
		this.recentPrice = recentPrice;
	}
	
	public String getDealYear() {
		return dealYear;
	}

	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}

	public String getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}

	public String getDealDay() {
		return dealDay;
	}

	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "HouseInfoDto [aptCode=" + aptCode + ", aptName=" + aptName + ", dongCode=" + dongCode + ", dongName="
				+ dongName + ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", buildYear=" + buildYear
				+ ", jibun=" + jibun + ", road=" + road + ", lat=" + lat + ", lng=" + lng + ", img=" + img
				+ ", recentPrice=" + recentPrice + ", dealYear=" + dealYear + ", dealMonth=" + dealMonth + ", dealDay="
				+ dealDay + ", area=" + area + ", floor=" + floor + "]";
	}

	

	
}
