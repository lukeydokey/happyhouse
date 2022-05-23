package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.SearchInfoDto;
import com.ssafy.happyhouse.model.service.SearchService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/search")
public class SearchController {
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private SearchService searchService;
	
	@ApiOperation(value = "유저 검색 기록을 입력 한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> search(@RequestBody SearchInfoDto searchInfo){
		logger.debug("search - 호출");
		try {
			if(searchService.selectSearchInfo(searchInfo)==null) {
				searchService.insertSearchInfo(searchInfo);
			}
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "유저 검색 탑 3 기록을 반환한다.")
	@GetMapping("/hotplace")
	public ResponseEntity<List<SearchInfoDto>> apt() throws Exception {
		return new ResponseEntity<List<SearchInfoDto>>(searchService.selectHotPlace(), HttpStatus.OK);
	}
}
