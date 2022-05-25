package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.AreaDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.HouseRecentPricesDto;
import com.ssafy.happyhouse.model.LikeAptDto;
import com.ssafy.happyhouse.model.ParkDto;
import com.ssafy.happyhouse.model.SchoolDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptInCoord(String lat1, String lng1, String lat2, String lng2) throws Exception;
	List<HouseInfoDto> getAptWithName(String dong, String apt) throws Exception;
	List<HouseDealDto> getAptDeal(String aptCode) throws Exception;
	List<SchoolDto> getSchool(String lat, String lng, String range) throws Exception;
	List<ParkDto> getPark(String lat, String lng, String range) throws Exception;
	List<AreaDto> getArea(String lat, String lng, String range) throws Exception;
	List<HouseRecentPricesDto>getAptRecentInfo(String aptCode) throws Exception;
	void save() throws Exception;
	void setLikeApt(LikeAptDto like);
	void deleteLikeApt(String id, String aptCode);
	List<LikeAptDto> getLikeApt(String id);
}
