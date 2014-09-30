package com.devuger.common.support.code;


public enum ProductCategory {
	WOMEN("WOMEN"),
	MEN("MEN"),
//	BABY_KIDS("베이비/키즈"),
	LIFE_STYLE("LIFE STYLE"),
	DIGITAL("TECH");
	
	private String name;

	private ProductCategory( String name) {
		this.name = name;
	} 

	public String getName() {
		return name;
	}
	
	public String getValue() {
		return this.toString();
	}
	
	public static String getName(String arg0) {
		return ProductCategory.valueOf(arg0).getName();
	}
}
