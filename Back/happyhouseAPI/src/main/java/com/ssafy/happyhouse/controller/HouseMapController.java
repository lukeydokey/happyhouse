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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.AreaDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.HouseRecentPricesDto;
import com.ssafy.happyhouse.model.ParkDto;
import com.ssafy.happyhouse.model.SchoolDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/map")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

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
	
	@GetMapping("/aptName")
	public ResponseEntity<List<HouseInfoDto>> aptName(@RequestParam("dong") String dong, @RequestParam("apt") String apt) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptWithName(dong, apt), HttpStatus.OK);
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
	public ResponseEntity<List<HouseRecentPricesDto>> aptRecentInfo(@PathVariable("aptCode") @ApiParam(value = "최근 거래가격 정보 가져올 아파트 코드", required = true) int aptCode) throws Exception {
		return new ResponseEntity<List<HouseRecentPricesDto>>(haHouseMapService.getAptRecentInfo(aptCode), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "아파트 실 거래가 파싱용 ")
	@GetMapping("/save")
	public void save() throws Exception{
		haHouseMapService.save();
	}
	
}
