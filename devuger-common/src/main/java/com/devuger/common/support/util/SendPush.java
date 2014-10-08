package com.devuger.common.support.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.devuger.common.entities.Device;

public class SendPush implements Runnable {

	private static Logger logger = Logger.getLogger(PushUtil.class);

	private String message;
	private String domain;
	private Device device;
	
	public SendPush(String message, Device device, String domain) {
		// TODO Auto-generated constructor stub
		this.message = message;
		this.device = device;
		this.domain = domain;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			List<BasicNameValuePair> basicNameValuePairs = new ArrayList<BasicNameValuePair>();
			basicNameValuePairs.add(new BasicNameValuePair("token", device.getToken()));
			basicNameValuePairs.add(new BasicNameValuePair("message", message));
			basicNameValuePairs.add(new BasicNameValuePair("os", device.getOs().getValue()));

			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(basicNameValuePairs, "UTF-8");

			String postUrl = String.format("http://%s/devices/push", domain);
			HttpPost httpPost = new HttpPost(postUrl);
			httpPost.setEntity(urlEncodedFormEntity);

			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			httpEntity.getContent();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}

}
