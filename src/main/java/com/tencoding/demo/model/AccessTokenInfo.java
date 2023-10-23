package com.tencoding.demo.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccessTokenInfo {

	private String accessToken;
	private String accessTokenTokenExpired;
	private String tokenType;
	private int expiresIn;
	
}