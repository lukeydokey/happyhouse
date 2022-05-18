package com.ssafy.happyhouse.parser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.happyhouse.model.HouseInfoDto;

public class GeoSAXHandler extends DefaultHandler {
	
	/**파상힌 위치 담을 아파트 정보*/
	private HouseInfoDto house;
	/**태그 바디 정보를 임시로 저장*/
	private String temp;
	public GeoSAXHandler(){}
	
	public void startElement(String uri, String localName
			                           , String qName, Attributes att ){
		if(qName.equals("point")){
			house = new HouseInfoDto();
		}
	}

	public void endElement(String uri, String localName, String qName){
		if(qName.equals("x")) { 
			house.setLng(temp);
		}else if(qName.equals("y")) { 
			house.setLat(temp);
		}
	}
	public void characters(char[]ch, int start, int length){
		temp = new String(ch, start, length);
	}
	public HouseInfoDto getHouseInfo() {
		return house;
	}
}
