package com.tencoding.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.tencoding.demo.model.AccessTokenInfo;

import lombok.Data;
import lombok.Getter;

@SpringBootTest
class DemoApplicationTests {
	
	@Value("${app.key}")
	String appKey;
	
	@Value("${app.secret}")
	String appSecret;
	
	@Test
	void contextLoads() {
		getAccessToken();

	}
	
	AccessTokenInfo getAccessToken() {
		WebClient webClient = WebClient
				.builder()
				.baseUrl("https://openapivts.koreainvestment.com:29443")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		
		Map<String, String> bodyMap = new HashMap<>();
		bodyMap.put("grant_type", "client_credentials");
		bodyMap.put("appkey", appKey);
		bodyMap.put("appsecret", appSecret);
		
		
		AccessTokenInfo accessTokenInfo = webClient
						.post()
						.uri("/oauth2/tokenP")
						.body(BodyInserters.fromValue(bodyMap))
						.retrieve()
						.bodyToMono(AccessTokenInfo.class)
						.block();
		return accessTokenInfo;
//		System.out.println(dd);
//		String accessToken = accessTokenInfo.getAccessToken();
	}
	
	void getDomesticStokCurrentPrice() {
		
		
		// TODO DB 조회해서 접근토큰 유효한지 보고 다시 가져올지 확인해야함
		// TODO request Header 값 지정
		// https://apiportal.koreainvestment.com/apiservice/apiservice-domestic-stock-quotations#L_07802512-4f49-4486-91b4-1050b6f5dc9d
		
		String FID1 = "FID_COND_MRKT_DIV_CODE"; // J
		
		// TODO 종목번호 6자리 입력해야함
		String FID2 = "FID_INPUT_ISCD"; // 005930
		
		
		String uri = "/uapi/domestic-stock/v1/quotations/inquire-price";
		
		BuildWebClient buildWebClient = new BuildWebClient();
		WebClient webClient = buildWebClient.getWebClient();
		webClient
				.get()
				.uri(uribuilder -> uribuilder
									.path(uri)
									.queryParam(FID1, "J")
									.queryParam(FID2, "005930")
									.build())
				.retrieve()
				.bodyToMono(String.class)
				.block();
				
	}
	
}

@Getter
class BuildWebClient{

	private WebClient webClient;
//	private Consumer<HttpHeaders> consumerHeaders;
//	private Map<String, String> bodyMap;
	
	public BuildWebClient() {
		this.webClient = WebClient
							.builder()
							.baseUrl("https://openapivts.koreainvestment.com:29443")
							.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
							.build();
	}
}
