package com.devuger.common.support.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.devuger.common.entities.Device;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseObject;
import com.devuger.common.support.code.DeviceOs;

@Component
public class PushUtil extends BaseObject {
	
//	private static Logger logger = Logger.getLogger(PushUtil.class);

	private static String domain = "api.cocoha.kr";
//	private static String domain = "210.95.44.173:8080";
	
	public String getDomain() {
		return domain;
	}

	@Value("${config.api.server}")
	public void setDomain(String domain) {
		PushUtil.domain = domain;
	}

	public static void send(User user, String message) {
		
		Assert.notNull(user, "수신인이 없습니다.");
		Assert.hasText(message, "전달할 메시지를 입력 해주세요.");
		
		send(user.getDevices(), message);		
	}
	
	public static void send(List<Device> devices, String message) {
		
		Assert.hasText(message, "전달할 메시지를 입력 해주세요.");
		
		if(devices != null) {
			for(Device device : devices) {
				SendPush sendPush = new SendPush(message, device, domain);
				Thread thread = new Thread(sendPush);
				thread.start();
			}
		}
	}

	public static void send(Device device, String message) {
		
		Assert.hasText(message, "전달할 메시지를 입력 해주세요.");
		
		if(device != null) {
			SendPush sendPush = new SendPush(message, device, domain);
			Thread thread = new Thread(sendPush);
			thread.start();
		}
	}

	/**
	 * @param args
	 */
	public static void main( String[] args )
	{
		Device device = new Device();
		device.setOs(DeviceOs.IPHONE);
		device.setToken("7e532dccad3cc00b043d055743f1ae8eb5827712cbe25604e3c5b52cec460ed8");
		
		List<Device> devices = new ArrayList<Device>();
		devices.add(device);
		
		User user = new User();
		user.setDevices(devices);

		PushUtil.send(user, "테스트");
	}
}
