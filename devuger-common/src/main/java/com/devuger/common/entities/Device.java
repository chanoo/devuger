package com.devuger.common.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.devuger.common.support.code.DeviceOs;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="devices")
@JsonInclude(Include.NON_EMPTY)
public class Device extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3927785555405803665L;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, length=20)
	private DeviceOs os;
	@Column(nullable=false, length=255)
	private String token;
	@Transient
	private String message;
	public DeviceOs getOs() {
		return os;
	}
	public void setOs(DeviceOs os) {
		this.os = os;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
