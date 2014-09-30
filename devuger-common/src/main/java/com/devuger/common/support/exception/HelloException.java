package com.devuger.common.support.exception;

public class HelloException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2945764263607404579L;
	
	private String message;
	private String redirect;
	private Object data;
	
	public HelloException(String message, int code, Object data)
	{
		this.message = message;
		this.data = data;
	}

	public HelloException(String message)
	{
		this.message = message;
	}
	
	public HelloException(String message, String redirect)
	{
		this.message = message;
		this.redirect = redirect;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getRedirect() {
		return redirect;
	}

	public Object getData() {
		return data;
	}
}
