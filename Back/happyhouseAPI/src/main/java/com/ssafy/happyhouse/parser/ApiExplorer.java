package com.ssafy.happyhouse.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ApiExplorer {
	
    public void getApi (String gCode, String Date){
    	StringBuilder sb = new StringBuilder();
    	
        try {
	    	StringBuilder urlBuilder = new StringBuilder("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=YE4iuvCFLqrX2uhAnUQyWnyvqIN8tZTGMxeI8O4PvUkj19xtA9r6c%2F6wddUdReXtMAk1AA05FQ7LvJD4ceS7sg%3D%3D"); /*카카오 계정 Service Key*/
//	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=qzf1cql0Ez5zQHvQqlaF8RsVuDUPRxQxZsZvCYqoEtGN22W%2FDb%2Blg5qPRGhcnnM2YJqOkchM2qV1U1X2mmnkAQ%3D%3D"); /*네이버 계정 Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("250", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("LAWD_CD","UTF-8") + "=" + URLEncoder.encode(gCode, "UTF-8")); /*지역코드*/
	        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD","UTF-8") + "=" + URLEncoder.encode(Date, "UTF-8")); /*계약월*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        String line;
	        while ((line = rd.readLine()) != null) {
	        	sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
        }
        catch(IOException e) {
        	e.printStackTrace();
        }
        try{
            File file = new File("apt.xml");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(sb.toString());
            bw.close();
         }catch(Exception e) {
            e.printStackTrace();
         }
    }
    public void getGeo(String address) {
    	StringBuilder sb = new StringBuilder();
    	
        try {
	    	StringBuilder urlBuilder = new StringBuilder("http://api.vworld.kr/req/address?service=address&request=getcoord&version=2.0&crs=epsg:4326&refine=true&simple=false&format=xml&type=road"); /*URL*/
	        urlBuilder.append("&" + URLEncoder.encode("key","UTF-8") + "=1CA3C5B5-EE24-35B0-A2BC-C4AB58DC80EF"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("address","UTF-8") + "=" + URLEncoder.encode(address,"UTF-8")); /*도로명 주소*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        String line;
	        while ((line = rd.readLine()) != null) {
	        	sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
        }
        catch(IOException e) {
        	e.printStackTrace();
        }
        try{
            File file = new File("geo.xml");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(sb.toString());
            bw.close();
         }catch(Exception e) {
            e.printStackTrace();
         }
    }
}