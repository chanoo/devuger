package com.devuger.common.support.code;

public enum DeviceOs {
	IPHONE("아이폰"),
	ANDROID("안드로이드");

	private String name;

	private DeviceOs( String name) {
		this.name = name;
	} 

	public String getName() {
		return name;
	}
	
	public String getValue() {
		return this.toString();
	}
}
