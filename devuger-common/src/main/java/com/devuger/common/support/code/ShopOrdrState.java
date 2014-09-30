package com.devuger.common.support.code;

public enum ShopOrdrState {
	REQUEST_SHIPPING("배송 요청"),
	CANCEL_SHIPPING("출고 취소"),
	COMPLETE_SHIPPING("출고 완료");
	
	private String name;

	private ShopOrdrState( String name) {
		this.name = name;
	} 

	public String getName() {
		return name;
	}
	
	public String getValue() {
		return this.toString();
	}
}
