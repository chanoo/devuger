package com.devuger.common.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devuger.common.support.json.DateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="notifications")
@JsonInclude(Include.NON_NULL)
public class Notification extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 3983926125916499731L;
  private String message;
  private boolean view;
  private String uri;
  @JsonSerialize(using = DateSerializer.class)
  @Column(nullable = false)
  private Date updatedOn = new Date();
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "updatedBy", nullable = false)
  private User updatedBy;
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public boolean isView() {
    return view;
  }
  public void setView(boolean view) {
    this.view = view;
  }
  public String getUri() {
    return uri;
  }
  public void setUri(String uri) {
    this.uri = uri;
  }
  public Date getUpdatedOn() {
    return updatedOn;
  }
  public void setUpdatedOn(Date updatedOn) {
    this.updatedOn = updatedOn;
  }
  public User getUpdatedBy() {
    return updatedBy;
  }
  public void setUpdatedBy(User updatedBy) {
    this.updatedBy = updatedBy;
  }
}
