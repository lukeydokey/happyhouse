package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HouseRecentPrices (최근 아파트 연도별 최대,최소 가격 정보)", description = "최근 아파트 연도별 최대,최소 가격 정보를 가진   Domain Class")
public class HouseRecentPricesDto {
	@ApiModelProperty(value = "아파트 코드")
	private int aptCode;
	@ApiModelProperty(value = "거래 년도")
	private String dealYear;
	@ApiModelProperty(value = "최소 가격")
	private String min;
	@ApiModelProperty(value = "최대 가격")
	private String max;
	public int getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	public String getDealYear() {
		return dealYear;
	}
	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	
	@Override
	public String toString() {
		return "HouseRecentPricesDto [aptCode=" + aptCode + ", dealYear=" + dealYear + ", min=" + min + ", max=" + max
				+ "]";
	}
	
}
