package com.ssafy.happyhouse.model.service;


import java.util.List;

import com.ssafy.happyhouse.model.SearchInfoDto;

public interface SearchService {
	public SearchInfoDto selectSearchInfo(SearchInfoDto searchInfo) throws Exception;
	public int insertSearchInfo (SearchInfoDto searchInfo) throws Exception;
	public List<SearchInfoDto> selectHotPlace() throws Exception;
	public List<SearchInfoDto> selectHotPlacebyGender(String gender) throws Exception;
	public List<SearchInfoDto> selectDongsbyDongName(String dongName) throws Exception;
}
