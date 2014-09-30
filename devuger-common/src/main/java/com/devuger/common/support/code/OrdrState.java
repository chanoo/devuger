package com.devuger.common.support.code;

public enum OrdrState {
	COMPLETE("주문 완료"),
	CANCEL("주문 취소");
	
	private String name;

	private OrdrState( String name) {
		this.name = name;
	} 

	public String getName() {
		return name;
	}
	
	public String getValue() {
		return this.toString();
	}
}
