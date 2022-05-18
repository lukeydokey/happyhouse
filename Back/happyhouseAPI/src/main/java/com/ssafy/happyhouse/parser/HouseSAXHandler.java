package com.ssafy.happyhouse.parser;


import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.happyhouse.model.HouseInfoDto;

public class HouseSAXHandler extends DefaultHandler {
	
	/**아파트 거래 정보를 담는다 */
	private List<HouseInfoDto> houses;
	/**파상힌 아파트 거래 정보*/
	private HouseInfoDto house;
	/**태그 바디 정보를 임시로 저장*/
	private String temp;
	public HouseSAXHandler(){
		houses = new ArrayList<HouseInfoDto>();
	}
	public void startElement(String uri, String localName
			                           , String qName, Attributes att ){
		if(qName.equals("item")){
			house = new HouseInfoDto();
		}
	}
	private String sigungu;
	private String dong;
	private String roadname;
	private String roadcode;
	public void endElement(String uri, String localName, String qName){
		if(qName.equals("법정동시군구코드")) { 
			sigungu = temp;
		}else if(qName.equals("법정동읍면동코드")) { 
			dong = temp.substring(0, 3)+"00";
		}else if(qName.equals("도로명")) { 
			roadname = temp;
		}else if(qName.equals("도로명건물본번호코드")) { 
			roadcode = Integer.parseInt(temp)+"";
		}else if(qName.equals("도로명건물부번호코드")) {
			if(Integer.parseInt(temp)>0)
				roadcode = roadcode + "-"+ Integer.parseInt(temp);
		}else if(qName.equals("아파트")) { 
			house.setAptName(temp.trim());
		}else if(qName.equals("거래금액")) { 
			house.setRecentPrice(temp.trim());
		}else if(qName.equals("법정동")) { 
			house.setDongName(temp.trim());
		}else if(qName.equals("년")) { 
			house.setDealYear(temp);
		}else if(qName.equals("월")) { 
			house.setDealMonth(temp);
		}else if(qName.equals("일")) { 
			house.setDealDay(temp);
		}else if(qName.equals("전용면적")) { 
			house.setArea(temp);
		}else if(qName.equals("층")) { 
			house.setFloor(temp);
		}else if(qName.equals("건축년도")) { 
			house.setBuildYear(Integer.parseInt(temp));
		}else if(qName.equals("지번")) { 
			house.setJibun(temp);
		}else if(qName.equals("item")) {
			house.setDongCode(sigungu+""+dong);
			house.setRoad(roadname+" "+roadcode);
			houses.add(house);
		}
	}
	public void characters(char[]ch, int start, int length){
		temp = new String(ch, start, length);
	}
	public List<HouseInfoDto> getHouseInfo() {
		return houses;
	}
}
