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

/**
 * 대시보드 테이블
 *  - 사용자별로 현황 정보 제공
 *  cf) 알림수, 팔로워수, 팔로윙수
 * 
 * @author hello
 *
 */
@Entity
@Table(name="dashboards")
@JsonInclude(Include.NON_NULL)
public class Dashboard extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7655117641213144664L;

  private int notificationCount;
  private int followerCount;
  private int followingCount;
  @JsonSerialize(using = DateSerializer.class)
  @Column(nullable = false)
  private Date updatedOn = new Date();
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "updatedBy", nullable = false)
  private User updatedBy;
  public int getNotificationCount() {
    return notificationCount;
  }
  public void setNotificationCount(int notificationCount) {
    this.notificationCount = notificationCount;
  }
  public int getFollowerCount() {
    return followerCount;
  }
  public void setFollowerCount(int followerCount) {
    this.followerCount = followerCount;
  }
  public int getFollowingCount() {
    return followingCount;
  }
  public void setFollowingCount(int followingCount) {
    this.followingCount = followingCount;
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
