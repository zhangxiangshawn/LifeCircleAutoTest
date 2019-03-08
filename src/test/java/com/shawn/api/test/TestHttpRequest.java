package com.shawn.api.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.shawn.api.validation.ResponseChecker;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.shawn.apitest02.HttpRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**  
 * @date: 2019年2月27日
 * @author: shawn
 *   
 */
public class TestHttpRequest {
	
	@Test
	public void testGet() throws ParseException, IOException{
		String url = "http://httpbin.org/get";
		HttpResponse response = new HttpRequest(url)
				.setHeaders(null)
				.setParams(null)
				.doGet();
		//输出响应
		System.out.println(EntityUtils.toString(response.getEntity()));
		//验证响应码
		int code = response.getStatusLine().getStatusCode();
		Assert.assertEquals(200, code);
	}
	
	@Test
	public void testPost() throws ParseException, IOException{
		String url = "http://httpbin.org/post";
		//header
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("Content-Type","application/json");

		HttpResponse response = new HttpRequest(url)
				.setHeaders(header)
				.setContent("{\"type\":\"ONLINE_SHIPS\"}")
				.doPost();
		//输出响应
		System.out.println(EntityUtils.toString(response.getEntity()));
		//验证响应码
		int code = response.getStatusLine().getStatusCode();
		Assert.assertEquals(200, code);
	}

//	@Test
//	public void test3() throws IOException {
//		String url = "http://www.httpbin.org/get";
//		HttpResponse response = new HttpRequest(url).doGet();
//
//		Map<String, Object> exceptMap = new HashMap<String,Object>();
//
//		exceptMap.put("base", "CNY");
//		exceptMap.put("date","2018-01-12");
//		exceptMap.put("rates.USD", new BigDecimal(0.15478).setScale(5, RoundingMode.HALF_UP));
//		exceptMap.put("rates.JPY", new BigDecimal(17.2).setScale(1, RoundingMode.HALF_UP));
//
//		ResponseChecker responseChecker = new ResponseChecker(response);
//		responseChecker.codeCheck(200);
//		responseChecker.dataCheck(exceptMap);
//	}

}
