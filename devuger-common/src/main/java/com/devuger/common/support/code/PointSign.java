package com.devuger.common.support.code;

public enum PointSign {
	PLUS("+"),
	MINUS("-");
	
	private String name;

	private PointSign( String name) {
		this.name = name;
	} 

	public String getName() {
		return name;
	}
	
	public String getValue() {
		return this.toString();
	}
}
