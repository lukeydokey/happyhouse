package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.SearchInfoDto;
import com.ssafy.happyhouse.model.service.SearchService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
	
	@ApiOperation(value = "동 이름을 검색해서 리스트를 반환한다.")
	@GetMapping("/{dongName}")
	public ResponseEntity<List<SearchInfoDto>> selectDongsbyDongName(@PathVariable("dongName") @ApiParam(value = "검색할 동 이름", required = true) String dongName) throws Exception {
		return new ResponseEntity<List<SearchInfoDto>>(searchService.selectDongsbyDongName(dongName), HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저 검색 탑 3 순위를 반환한다.")
	@GetMapping("/hotplace")
	public ResponseEntity<List<SearchInfoDto>> searchRank() throws Exception {
		return new ResponseEntity<List<SearchInfoDto>>(searchService.selectHotPlace(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저 성별 검색 순위를 반환한다.")
	@GetMapping("/hotplace/{gender}")
	public ResponseEntity<List<SearchInfoDto>> searchRankByGender(@PathVariable("gender") @ApiParam(value = "순위를 받아올 성별", required = true) String gender) throws Exception {
		return new ResponseEntity<List<SearchInfoDto>>(searchService.selectHotPlacebyGender(gender), HttpStatus.OK);
	}
}
