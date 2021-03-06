package com.devuger.common.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 코멘트 테이블
 * 
 * @author hello
 *
 */
@JsonAutoDetect
@Entity
@Table(name="comments")
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Comment extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 46269968577379661L;
  
  @ManyToOne
  @JoinColumn(name = "feed", nullable = false)
  private Feed feed;
	@Column(columnDefinition="TEXT")
	private String content;
  public Feed getFeed() {
    return feed;
  }
  public void setFeed(Feed feed) {
    this.feed = feed;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
}
