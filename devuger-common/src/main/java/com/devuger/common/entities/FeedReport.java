package com.devuger.common.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 피드 신고 테이블
 * 
 * @author hello
 *
 */
@Entity
@Table(name="feed_reports")
@JsonInclude(Include.NON_NULL)
public class FeedReport extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -1907218816750055462L;
  @ManyToOne
  @JoinColumn(name = "feed", nullable = false)
  private Feed feed;
  @Column(nullable=false, length=20)
  private String why;
  public Feed getFeed() {
    return feed;
  }
  public void setFeed(Feed feed) {
    this.feed = feed;
  }
  public String getWhy() {
    return why;
  }
  public void setWhy(String why) {
    this.why = why;
  }
}
