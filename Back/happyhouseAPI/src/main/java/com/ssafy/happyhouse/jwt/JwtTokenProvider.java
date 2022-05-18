package com.ssafy.happyhouse.jwt;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
    
public class JwtTokenProvider {
    //암복호화에 사용되는 키 값 입니다.
    private static String secretKey = Base64.getEncoder().encodeToString("secretkeysecret".getBytes());
    public String createToken(String uid) {
        Claims claims = Jwts.claims();  //나중에 서버에서 파싱해서 볼 데이터 입니다.
        claims.put("userId", uid);
        Date now = new Date();
        
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) 
                .setClaims(claims) // 데이터를 넣어 줍니다
                .setIssuedAt(now)   // 토큰 발행 일자
                .setExpiration(new Date(now.getTime() + (1000L * 60 * 10))) // 만료 기간 입니다
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘과 암복호화에 사용할 키를 넣어줍니다
                .compact(); // Token 생성
    }
    
    // Jwt Token의 유효성 및 만료 기간 검사합니다
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
    
    // Jwt Token에서 데이터를 전달 합니다.
    public Claims getInformation(String token) {
        Claims claims =Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims;
    }
    
    
}