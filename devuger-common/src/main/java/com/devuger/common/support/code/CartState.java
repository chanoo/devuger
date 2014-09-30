package com.devuger.common.support.code;

public enum CartState {
	ADDED("추가"),
	ORDERD("주문 완료"), // 취소 가능
	READY("출고 준비"), // 취소 가능, 판매자 협의가 필요
	SHIPPED("배송 중"),
	COMPLETED("배송 완료"),
	SOLD_OUT("품절"),
	RETURN("반품/환불"),
	CANCEL("주문 취소");
	
	private String name;

	private CartState(String name) {
		this.name = name;
	} 

	public String getName() {
		return name;
	}
	
	public String getValue() {
		return this.toString();
	}

}
