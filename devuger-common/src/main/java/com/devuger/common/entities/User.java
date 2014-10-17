package com.devuger.common.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 사용자 테이블
 * 
 * @author hello
 *
 */
@Entity
@Table(name="users")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({"createdBy"})
public class User extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1827889442772094376L;
  @Column(nullable=false, length=100)
  private String username;
  @Column(nullable=false, length=255)
  private String email;
  @JsonIgnore
  @Column(nullable=false, length=255)
  private String hashedPassword;
  @Column(nullable=false, length=255)
  private String token;
  @JsonIgnore
  @Column(nullable=false)
  private Date lastSigninDate;
  @JsonIgnore
  @Column(nullable=false, length=20)
  private String lastSigninIp;
  @Transient
  private String hashedPasswordConfirm;
  @JsonIgnore
  @Transient
  private boolean serviceTerms;
  @JsonIgnore
  @Transient
  private boolean userInfoTerms;
  @JsonIgnore
  @OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER) 
  private List<Device> devices;
  @JsonIgnore
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy="createdBy", fetch=FetchType.LAZY)
  private List<Comment> comments;
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getHashedPassword() {
    return hashedPassword;
  }
  public void setHashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }
  public String getHashedPasswordConfirm() {
    return hashedPasswordConfirm;
  }
  public void setHashedPasswordConfirm(String hashedPasswordConfirm) {
    this.hashedPasswordConfirm = hashedPasswordConfirm;
  }
  public boolean isServiceTerms() {
    return serviceTerms;
  }
  public void setServiceTerms(boolean serviceTerms) {
    this.serviceTerms = serviceTerms;
  }
  public boolean isUserInfoTerms() {
    return userInfoTerms;
  }
  public void setUserInfoTerms(boolean userInfoTerms) {
    this.userInfoTerms = userInfoTerms;
  }
  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }
  public Date getLastSigninDate() {
    return lastSigninDate;
  }
  public void setLastSigninDate(Date lastSigninDate) {
    this.lastSigninDate = lastSigninDate;
  }
  public String getLastSigninIp() {
    return lastSigninIp;
  }
  public void setLastSigninIp(String lastSigninIp) {
    this.lastSigninIp = lastSigninIp;
  }
  public List<Device> getDevices() {
    return devices;
  }
  public void setDevices(List<Device> devices) {
    this.devices = devices;
  }
  public List<Comment> getComments() {
    return comments;
  }
  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }
}
