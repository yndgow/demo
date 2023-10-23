package com.tencoding.demo.model;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.Data;

@Data
public class HttpHeadersToMap {

	private String contentType;
	private String authorization;
	private String appkey;
	private String appsecret;
	private String trId = "FHKST01010100";
	
	public MultiValueMap<String, String> toMultiValueMap(){
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("content-type", contentType);
		map.add("authorization", contentType);
		map.add("appkey", appkey);
		map.add("appsecret", appsecret);
		map.add("tr_id", trId);
		return map;
	}
}
