package com.devuger.common.support.base;

import org.springframework.core.Conventions;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class BaseResult extends ModelMap {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8989346688358642884L;
	/**
	 * 
	 */	
	public static String RESULT_SUCESSS = "success";
	public static String RESULT_ERROR = "error";

	private String result = RESULT_SUCESSS;
	private String message = "처리되었습니다.";
	private String desc;
	private String redirect;

	public BaseResult(String message, String result) {
		
		this.message = message;
		this.result = result;
		
		this.addAttribute("result", result);
		this.addAttribute("message", message);
		if(desc != null)
		this.addAttribute("desc", desc);
		if(redirect != null)
		this.addAttribute("redirect", redirect);
	}

	public BaseResult(String message) {
		
		this.message = message;
		
		this.addAttribute("result", result);
		this.addAttribute("message", message);
		if(desc != null)
		this.addAttribute("desc", desc);
		if(redirect != null)
		this.addAttribute("redirect", redirect);
	}

	public BaseResult() {
		
		this.result = RESULT_SUCESSS;
		this.message = "처리 되었습니다.";
		
		this.addAttribute("result", result);
		this.addAttribute("message", message);
		if(desc != null)
		this.addAttribute("desc", desc);
		if(redirect != null)
		this.addAttribute("redirect", redirect);
	}
	
	@Override
	public ModelMap addAttribute(String attributeName, Object attributeValue) {
		Assert.notNull(attributeName, "Model attribute name must not be null");
		if(attributeValue != null) {
			put(attributeName, attributeValue);
		}
		return this;
	}

	@Override
	public ModelMap addAttribute(Object attributeValue) {
		if(attributeValue == null)
			return this;
		return addAttribute(Conventions.getVariableName(attributeValue), attributeValue);
	}

	public void setResult(String result) {
		this.addAttribute("result", result);
	}
	public void setMessage(String message) {
		this.addAttribute("message", message);
	}
	public void setDesc(String desc) {
		this.addAttribute("desc", desc);
	}
	public void setRedirect(String redirect) {
		this.addAttribute("redirect", redirect);
	}
}
