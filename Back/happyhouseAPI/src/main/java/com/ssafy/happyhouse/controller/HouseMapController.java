package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.AreaDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.HouseRecentPricesDto;
import com.ssafy.happyhouse.model.LikeAptDto;
import com.ssafy.happyhouse.model.ParkDto;
import com.ssafy.happyhouse.model.SchoolDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/map")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private HouseMapService haHouseMapService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	@GetMapping("/coord")
	public ResponseEntity<List<HouseInfoDto>> coord(@RequestParam("lat1") String lat1, @RequestParam("lng1") String lng1, @RequestParam("lat2") String lat2, @RequestParam("lng2") String lng2) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInCoord(lat1, lng1, lat2, lng2), HttpStatus.OK);
	}
	
	@GetMapping("/aptDeal")
	public ResponseEntity<List<HouseDealDto>> aptDeal(@RequestParam("aptCode") String aptCode) throws Exception {
		return new ResponseEntity<List<HouseDealDto>>(haHouseMapService.getAptDeal(aptCode), HttpStatus.OK);
	}
	@GetMapping("/area")
	public ResponseEntity<List<AreaDto>> school(@RequestParam("lat") String lat, @RequestParam("lng") String lng, @RequestParam("range") String range) throws Exception {
		return new ResponseEntity<List<AreaDto>>(haHouseMapService.getArea(lat, lng, range), HttpStatus.OK);
	}
	@GetMapping("/aptRecentInfo/{aptCode}")
	public ResponseEntity<List<HouseRecentPricesDto>> aptRecentInfo(@PathVariable("aptCode") @ApiParam(value = "?????? ???????????? ?????? ????????? ????????? ??????", required = true) String aptCode) throws Exception {
		return new ResponseEntity<List<HouseRecentPricesDto>>(haHouseMapService.getAptRecentInfo(aptCode), HttpStatus.OK);
	}
	@GetMapping("/likeapt")
	public ResponseEntity<List<LikeAptDto>> getlikeapt(@RequestParam("id") String id)throws Exception {
		return new ResponseEntity<List<LikeAptDto>>(haHouseMapService.getLikeApt(id), HttpStatus.OK);
	}

	@PostMapping("/likeapt")
	public ResponseEntity<String> likeapt(@RequestBody LikeAptDto like){
		try {
			haHouseMapService.setLikeApt(like);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	@DeleteMapping("/likeapt")
	public ResponseEntity<String> deletelikeapt(@RequestParam("id") String id, @RequestParam("aptCode") String aptCode){
		try {
			haHouseMapService.deleteLikeApt(id, aptCode);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
}
