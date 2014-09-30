package com.devuger.common.support.base;

public class CompareDomain 
{
	private String fieldId = null;
	private Object beforeData = null;
	private Object afterData = null;
	
	public Object getAfterData() {
		return afterData;
	}
	public void setAfterData(Object afterData) {
		this.afterData = afterData;
	}
	public Object getBeforeData() {
		return beforeData;
	}
	public void setBeforeData(Object beforeData) {
		this.beforeData = beforeData;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
}
