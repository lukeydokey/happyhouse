package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;
import com.ssafy.happyhouse.parser.ApiExplorer;
import com.ssafy.happyhouse.parser.GeoSAXHandler;
import com.ssafy.happyhouse.parser.HouseSAXHandler;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	

	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return houseMapMapper.getAptInDong(dong);
	}

	@Override
	public List<HouseInfoDto> getAptWithName(String dong, String apt) throws Exception {
		List<HouseInfoDto> list = houseMapMapper.getAptInDong(dong);
		List<HouseInfoDto> ret = new ArrayList<HouseInfoDto>();
		for (HouseInfoDto aptInfo : list) {
			char[] realName = aptInfo.getAptName().toCharArray();
			char[] keyword = apt.toCharArray();
			int rLength = realName.length, kLength = keyword.length;
			int[] ki = new int[kLength];
			for (int i = 1, j = 0; i < kLength; i++) {
				while (j > 0 && keyword[i] != keyword[j]) j = ki[j - 1];
				if (keyword[i] == keyword[j]) ki[i] = ++j;
				else ki[i] = 0;
			}
			int cnt = 0;
			List<Integer> l = new ArrayList<Integer>();
			for (int i = 0, j = 0; i < rLength; ++i) {
				while (j > 0 && realName[i] != keyword[j]) j = ki[j - 1];
				if (realName[i] == keyword[j]) {
					if (j == kLength - 1) {
						cnt++;
						l.add((i + 1) - kLength);
						j = ki[j];
					} else j++;
				}
			}
			if (cnt > 0) ret.add(aptInfo);
		}
		return ret;
	}
	
	
	private ApiExplorer api = new ApiExplorer();
	private SAXParserFactory factory = SAXParserFactory.newInstance();
	@Override
	@Transactional
	public void save() throws Exception {
		List<SidoGugunCodeDto> list = houseMapMapper.getGugun();
		
		int cntApt = houseMapMapper.countApt() + 1;
		for(SidoGugunCodeDto gugun : list) {
			if(Long.parseLong(gugun.getGugunCode())>2700000000L) {
				System.out.println("break");
				break;
//				continue;
			}
			for(int year=2019;year<=2019;year++) {
				for(int month=1;month<=12;month++) {
					SAXParser parser = factory.newSAXParser();
					HouseSAXHandler handler = new HouseSAXHandler();
					String yearStr = String.valueOf(year).concat("00");
					String date = String.valueOf(Integer.parseInt(yearStr)+month);
					
					api.getApi(gugun.getGugunCode().substring(0, 5), date);
					parser.parse("apt.xml", handler);
					List<HouseInfoDto> houses = handler.getHouseInfo();
					List<HouseInfoDto> houseInfos = new ArrayList<>();
					List<HouseInfoDto> houseDeals = new ArrayList<>();
					for(HouseInfoDto house : houses) {
						HouseInfoDto tmp = houseMapMapper.getApt(house);
						if(tmp==null) {
							boolean flag = false;
							for(HouseInfoDto info : houseInfos) {
								if(info.getAptName().equals(house.getAptName())&&info.getDongCode().equals(house.getDongCode())) {
									flag = true;
									house.setAptCode(info.getAptCode());
									break;
								}
							}
							SidoGugunCodeDto geo = houseMapMapper.getSidoGugunDong(house.getDongCode());
							GeoSAXHandler handle = new GeoSAXHandler();
							if(geo == null)
								continue;
							api.getGeo(geo.getGugunName()+" "+ house.getRoad());
							parser.parse("geo.xml", handle);
							HouseInfoDto latlng = handle.getHouseInfo();
							if(latlng==null) {
								house.setLat(null);
								house.setLng(null);
							}else {
								house.setLat(latlng.getLat());
								house.setLng(latlng.getLng());
							}
							if(!flag) {
								house.setAptCode(cntApt++);
								houseInfos.add(house);
							}
						}else {
							house.setAptCode(tmp.getAptCode());
						}
//						boolean flag = false;
//						for(HouseInfoDto deal : houseDeals) {
//							if(deal.getAptCode()==house.getAptCode() && deal.getRecentPrice().equals(house.getRecentPrice())
//									&& deal.getDealYear().equals(house.getDealYear()) && deal.getDealMonth().equals(house.getDealMonth())
//									&& deal.getDealDay().equals(house.getDealDay()) && deal.getFloor().equals(house.getFloor())) {
//								flag = true;
//								break;
//							}
//						}
//						if(!flag)
						houseDeals.add(house);
					}
					
					if(!houseInfos.isEmpty())
						houseMapMapper.saveAptInfo(houseInfos);
					if(!houseDeals.isEmpty())
						houseMapMapper.saveAptDeal(houseDeals);
					
				}
			}
		}
		
		System.out.println("Transaction success");
	}

}
