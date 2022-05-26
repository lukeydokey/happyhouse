package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HouseDeal (아파트 매물)", description = "아파트 매물 정보를 가진   Domain Class")
public class HouseDealDto {

	@ApiModelProperty(value = "매물 번호")
	private String no;
	@ApiModelProperty(value = "아파트 코드")
	private String aptCode;
	@ApiModelProperty(value = "거래 가격")
	private String dealAmount;
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
	@ApiModelProperty(value = "분류")
	private String type;
	@ApiModelProperty(value = "임대료")
	private String rentMoney;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRentMoney() {
		return rentMoney;
	}
	public void setRentMoney(String rentMoney) {
		this.rentMoney = rentMoney;
	}
	@Override
	public String toString() {
		return "HouseDealDto [no=" + no + ", aptCode=" + aptCode + ", dealAmount=" + dealAmount + ", dealYear="
				+ dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor
				+ ", type=" + type + ", rentMoney=" + rentMoney + "]";
	}
	

	

	

	
}
