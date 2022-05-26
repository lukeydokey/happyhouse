package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.AreaDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.HouseRecentPricesDto;
import com.ssafy.happyhouse.model.LikeAptDto;
import com.ssafy.happyhouse.model.ParkDto;
import com.ssafy.happyhouse.model.SchoolDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

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
	public List<HouseInfoDto> getAptInCoord(String lat1, String lng1, String lat2, String lng2) throws Exception {
		return houseMapMapper.getAptInCoord(lat1, lng1, lat2, lng2);
	}
	@Override
	public List<HouseDealDto> getAptDeal(String aptCode) throws Exception {
		return houseMapMapper.getAptDeal(aptCode);
	}
	
	@Override
	public List<HouseRecentPricesDto> getAptRecentInfo(String aptCode) throws Exception {
		return houseMapMapper.getAptRecentInfo(aptCode);
	}
	
	@Override
	public List<SchoolDto> getSchool(String lat, String lng, String range) throws Exception {
		return houseMapMapper.getSchool(lat, lng, range);
	}

	@Override
	public List<ParkDto> getPark(String lat, String lng, String range) throws Exception {
		return houseMapMapper.getPark(lat, lng, range);
	}
	@Override
	public List<AreaDto> getArea(String lat, String lng, String range) throws Exception {
		return houseMapMapper.getArea(lat, lng, range);
	}
	@Override
	public void setLikeApt(LikeAptDto like) {
		houseMapMapper.insertLikeApt(like);
	}
	@Override
	public void deleteLikeApt(String id, String aptCode) {
		houseMapMapper.deleteLikeApt(id, aptCode);
	}
	@Override
	public List<LikeAptDto> getLikeApt(String id) {
		return houseMapMapper.getLikeApt(id);
	}

}
