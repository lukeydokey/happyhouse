package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.SearchInfoDto;

@Mapper
public interface SearchMapper {
	public SearchInfoDto selectSearchInfo(SearchInfoDto searchInfo) throws SQLException;
	public int insertSearchInfo (SearchInfoDto searchInfo) throws SQLException;
	public List<SearchInfoDto> selectHotPlace() throws SQLException;
	public List<SearchInfoDto> selectHotPlacebyGender(String gender) throws SQLException;
	public List<SearchInfoDto> selectDongsbyDongName(String dongName) throws SQLException;
}
