package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.SearchInfoDto;
import com.ssafy.happyhouse.model.mapper.SearchMapper;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchMapper searchMapper;

	@Override
	public SearchInfoDto selectSearchInfo(SearchInfoDto searchInfo) throws Exception{
		return searchMapper.selectSearchInfo(searchInfo);
	}

	@Override
	public int insertSearchInfo(SearchInfoDto searchInfo) throws Exception {
		return searchMapper.insertSearchInfo(searchInfo);
	}

	@Override
	public List<SearchInfoDto> selectHotPlace() throws Exception {
		return searchMapper.selectHotPlace();
	}
	
	
}
