package com.devuger.common.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="users")
@JsonInclude(Include.NON_NULL)
public class User extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1827889442772094376L;
  @Column(nullable=false, length=255)
  private String email;
  @Column(nullable=false, length=100)
  private String username;
  @Column(nullable=false, length=255)
  private String hashedPassword;
  @Transient
  private String hashedPasswordConfirm;
  @Transient
  private boolean serviceTerms;
  @Transient
  private boolean userInfoTerms;

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

}