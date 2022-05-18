package com.ssafy.happyhouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "아이디 중복 체크 요청을 한다. 그리고 출력결과 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/idCheck")
	public ResponseEntity<String> idCheck(@RequestBody UserDto user) throws Exception{
		logger.debug("idCheck - 호출");
		if (userService.idCheck(user.getId()) == 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "로그인 요청을 한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> login(@RequestBody UserDto user) throws Exception{
		logger.debug("login - 호출");
		if (userService.login(user) != null) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "계정 생성 요청을 한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserDto user){
		logger.debug("register - 호출");
		try {
			userService.insertUser(user);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
}

//
//@Controller
//@RequestMapping("/user")
//@CrossOrigin("*")
//public class UserController {
//	
//private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//
//	@Autowired
//	private UserService userService;
//	
//	private JwtTokenProvider jwtProvider = new JwtTokenProvider();
//	
//	// 로그인
//	@PostMapping("/login")
//	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session,
//			RedirectAttributes rttr) throws Exception {
//		UserDto userDto = new UserDto(map.get("id"), map.get("password"));
//		UserDto user = userService.login(userDto);
//		if (user != null) {
//			System.out.println(user);
//			String jwt = jwtProvider.createToken(user.getId());
//			session.setAttribute("jwt", jwt);
//			session.setAttribute("userinfo", user);
//			return "redirect:/";
//		} else {
//			rttr.addFlashAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요");
//			System.out.println("실패");
//			return "redirect:/";
//		}
//	}
//	
//	// 로그아웃
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/";
//	}
//	
//	// 회원가입
//	@PostMapping(value = "/register")
//	public String userRegister(UserDto userDto) throws Exception {
//		userService.insertUser(userDto);
//		return "redirect:/";
//	}
//	
//
//}