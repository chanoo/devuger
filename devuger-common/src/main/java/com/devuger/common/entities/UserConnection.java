package com.devuger.common.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devuger.common.support.json.DateSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 페이스북 연동 테이블
 * 
 * @author hello
 *
 */
@JsonAutoDetect
@Entity
@Table(name="user_connections")
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserConnection extends AbstractEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5074175367702973943L;
  @Column(nullable = false)
  private String token;
  @Column(nullable = false)
  private String userId;
  @JsonSerialize(using = DateSerializer.class)
  @Column(nullable = false)
  private Date updatedOn = new Date();
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "updatedBy", nullable = false)
  private User updatedBy;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}
