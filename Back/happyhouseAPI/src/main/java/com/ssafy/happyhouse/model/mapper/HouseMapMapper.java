package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.HouseRecentPricesDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugun() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	SidoGugunCodeDto getSidoGugunDong (String dongCode) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	List<HouseDealDto> getAptDeal(String aptCode) throws SQLException;
	List<HouseRecentPricesDto>getAptRecentInfo(int aptCode) throws SQLException;
	HouseInfoDto getApt(HouseInfoDto houseInfo) throws SQLException;
	int countApt() throws SQLException;
	void saveAptInfo(List<HouseInfoDto> houseInfos) throws SQLException;
	void saveAptDeal(List<HouseInfoDto> houseDeals) throws SQLException;
}
