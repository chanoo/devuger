package com.devuger.common.support.code;

public enum UserTokenDevice {
	ADMIN_WEB("관리자 웹"),
	SHOP_IPHONE("상점 아이폰"),
	SHOP_ANDROID("상점 안드로이드"),
	SHOP_WEB("상점 웹"),
	SHOP_AIR("상점 에어"),
	MALL_WEB("쇼핑몰 웹"),
	USER_IPHONE("사용자 아이폰"),
	USER_ANDROID("사용자 안드로이드"),
	USER_WEB("사용자 웹");

	private String name;

	private UserTokenDevice( String name) {
		this.name = name;
	} 

	public String getName() {
		return name;
	}
	
	public String getValue() {
		return this.toString();
	}
}
