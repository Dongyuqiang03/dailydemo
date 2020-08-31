package com.example.dyq.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Httpclient {
	
	private static Logger logger= LoggerFactory.getLogger(Httpclient.class);
	
	public static final String METHOD_POST = "POST";
	public static final String METHOD_GET = "GET";
	public static final String CHARACTER_ENCODING = "UTF-8";

	
	public static String sendRequestMethod(Map<String, String> map, String url, String method, int timeout) throws Exception{
		
		// 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault(); 
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout*1000)
                .setConnectTimeout(timeout*1000)
                .setConnectionRequestTimeout(timeout*1000)
                .build();
        
        try {
        	List<NameValuePair> params = new ArrayList<NameValuePair>();
	        Set<Map.Entry<String, String>> entrySet = map.entrySet();
	        for (Map.Entry<String, String> e : entrySet) {
	            String name = e.getKey();
	            String value = e.getValue();
	            NameValuePair pair = new BasicNameValuePair(name, value);
	            params.add(pair);
	        }
	        
	        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, "UTF-8");
	        
	        if(logger.isDebugEnabled()){
    			logger.debug("http client url:"+url);
    			logger.debug("http client params:"+params.toString());
	        }
	        HttpUriRequest reqMethod = null;
	        if (METHOD_POST.equalsIgnoreCase(method)) {
	        	reqMethod = RequestBuilder.post().setUri(url).setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//	        			.setCharset(java.nio.charset.Charset.forName("UTF-8"))
	                    .addParameters(params.toArray(new BasicNameValuePair[params.size()]))
	        			.setEntity(urlEncodedFormEntity)
	                    .setConfig(requestConfig).build();
	        }else if(METHOD_GET.equalsIgnoreCase(method)) {
	        	reqMethod = RequestBuilder.get().setUri(url).setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
	        			.setEntity(urlEncodedFormEntity)
//	                    .addParameters(params.toArray(new BasicNameValuePair[params.size()]))
	                    .setConfig(requestConfig).build();
			}else{
				logger.warn("method unknow, return null.");
	        	return null;
			}
	        CloseableHttpResponse response = null;
	        if(httpclient != null)
	        	response = httpclient.execute(reqMethod);
	        
	        if(response != null && response.getStatusLine().getStatusCode() == 200)
	        	return EntityUtils.toString(response.getEntity(), "UTF-8");
	        else{
	        	if(response != null)
	        		logger.warn("http response status error, status{}, return null"+response.getStatusLine().getStatusCode());
	        	return null;
	        }
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		} finally{
			if(httpclient != null)
				httpclient.close();
		}
	}





	public static String sendRequestMethodWithHeader(String params, String url, String method, int timeout) throws Exception{

		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout*1000)
				.setConnectTimeout(timeout*1000)
				.setConnectionRequestTimeout(timeout*1000)
				.build();

		try {
//			List<NameValuePair> params = new ArrayList<NameValuePair>();
//			Set<Map.Entry<String, String>> entrySet = map.entrySet();
//			for (Map.Entry<String, String> e : entrySet) {
//				String name = e.getKey();
//				String value = e.getValue();
//				NameValuePair pair = new BasicNameValuePair(name, value);
//				params.add(pair);
//			}
//
//			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, "UTF-8");

			if(logger.isDebugEnabled()){
				logger.debug("http client url:"+url);
				logger.debug("http client params:"+params);
			}
			HttpUriRequest reqMethod = null;
			long random = System.currentTimeMillis();
			String message = EncryptUtil.sha2Encrypt(random+"1qaz2wsx1qaz2wsx");
			if(message == null){
				logger.error("vip头参数加密异常");
				return null;
			}
			message = message.toUpperCase();
			if (METHOD_POST.equalsIgnoreCase(method)) {
				reqMethod = RequestBuilder.post().setUri(url).setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
						.setHeader("random",String.valueOf(random))
						.setHeader("message",message)
						.setHeader("appid","TM000001")
//	        			.setCharset(java.nio.charset.Charset.forName("UTF-8"))
//	                    .addParameters(params.toArray(new BasicNameValuePair[params.size()]))
						.setEntity(new StringEntity(params))
						.setConfig(requestConfig).build();
			}else if(METHOD_GET.equalsIgnoreCase(method)) {
				reqMethod = RequestBuilder.get().setUri(url).setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
						.setEntity(new StringEntity(params))
//	                    .addParameters(params.toArray(new BasicNameValuePair[params.size()]))
						.setConfig(requestConfig).build();
			}else{
				logger.warn("method unknow, return null.");
				return null;
			}
			CloseableHttpResponse response = null;
			if(httpclient != null)
				response = httpclient.execute(reqMethod);

			if(response != null && response.getStatusLine().getStatusCode() == 200)
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			else{
				if(response != null)
					logger.warn("http response status error, status{}, return null"+response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		} finally{
			if(httpclient != null)
				httpclient.close();
		}
	}


	/**
	 * @Title: sendJsonStr
	 * @Description: post发送json字符串
	 * @param url
	 * @param params
	 * @return 返回数据
	 * @author Mundo
	 */
	public static String sendJsonStr(String url, String params) {
		String result = "";

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.addHeader("Content-type", "application/json; charset=utf-8");
			httpPost.setHeader("Accept", "application/json");
			if (StringUtils.isNotBlank(params)) {
				httpPost.setEntity(new StringEntity(params, Charset.forName("UTF-8")));
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity());
				logger.info("返回数据：" + result);
			} else {
				logger.info("请求失败");
			}
		} catch (IOException e) {
			logger.error("请求异常");
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return result;
	}



	public static String sendRequestMethodWithHeader(Map<String, String> map, String url, String method, int timeout) throws Exception{

		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout*1000)
				.setConnectTimeout(timeout*1000)
				.setConnectionRequestTimeout(timeout*1000)
				.build();

		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			Set<Map.Entry<String, String>> entrySet = map.entrySet();
			for (Map.Entry<String, String> e : entrySet) {
				String name = e.getKey();
				String value = e.getValue();
				NameValuePair pair = new BasicNameValuePair(name, value);
				params.add(pair);
			}

			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, "UTF-8");

			if(logger.isDebugEnabled()){
				logger.debug("http client url:"+url);
				logger.debug("http client params:"+params.toString());
			}
			HttpUriRequest reqMethod = null;
			long random = System.currentTimeMillis();
			String message = EncryptUtil.sha2Encrypt(random+"16406F0F5XGC65046A6F7FEAJ65C2CBE");
			if(message == null){
				logger.error("vip头参数加密异常");
				return null;
			}
			message = message.toUpperCase();
			if (METHOD_POST.equalsIgnoreCase(method)) {
				reqMethod = RequestBuilder.post().setUri(url).setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
						.setHeader("random",String.valueOf(random))
						.setHeader("message",message)
						.setHeader("appid","TM000003")
//	        			.setCharset(java.nio.charset.Charset.forName("UTF-8"))
//	                    .addParameters(params.toArray(new BasicNameValuePair[params.size()]))
						.setEntity(urlEncodedFormEntity)
						.setConfig(requestConfig).build();
			}else if(METHOD_GET.equalsIgnoreCase(method)) {
				reqMethod = RequestBuilder.get().setUri(url).setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
						.setEntity(urlEncodedFormEntity)
//	                    .addParameters(params.toArray(new BasicNameValuePair[params.size()]))
						.setConfig(requestConfig).build();
			}else{
				logger.warn("method unknow, return null.");
				return null;
			}
			CloseableHttpResponse response = null;
			if(httpclient != null)
				response = httpclient.execute(reqMethod);

			if(response != null && response.getStatusLine().getStatusCode() == 200)
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			else{
				if(response != null)
					logger.warn("http response status error, status{}, return null"+response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		} finally{
			if(httpclient != null)
				httpclient.close();
		}
	}


	public static String sendJsonRequestMethod(String json, String url, String method,int timeout) throws Exception{

		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout*1000)
				.setConnectTimeout(timeout*1000)
				.setConnectionRequestTimeout(timeout*1000)
				.build();
		HttpPost httpPost = new HttpPost(url);
		try {
			StringEntity entity = new StringEntity(json.toString(),"utf-8");//解决中文乱码问题
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);

			if(logger.isDebugEnabled())
				logger.debug("executing request :{}"+httpPost.getRequestLine());

			HttpUriRequest reqMethod = RequestBuilder.post().setUri(url).setEntity(entity).setConfig(requestConfig).build();
			CloseableHttpResponse response = null;
			if(httpclient != null)
				response = httpclient.execute(reqMethod);

			if(response != null && response.getStatusLine().getStatusCode() == 200)
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			else{
				if(response != null)
					logger.warn(" status code {} "+response.getStatusLine().getStatusCode());
				logger.warn(" server error, return null");
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		} finally{
			if(httpclient != null)
				httpclient.close();
		}
	}


}
