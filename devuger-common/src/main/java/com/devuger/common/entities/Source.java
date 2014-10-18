package com.devuger.common.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 좋아요 테이블
 * 
 * @author hello
 *
 */
@Entity
@Table(name="sources")
@JsonInclude(Include.NON_NULL)
public class Source extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -1907218816750055462L;

  @ManyToOne
  @JoinColumn(name = "feed", nullable = false)
  private Feed feed;
  @JsonIgnore
  @Column(nullable=false, columnDefinition="TEXT")
  private String code; // 코드
  @Transient
  private String codeEscape;
  @Column(nullable=true, length=255)
  private String comment; // 코멘트
  public Feed getFeed() {
    return feed;
  }
  public void setFeed(Feed feed) {
    this.feed = feed;
  }
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getCodeEscape() {
    return HtmlUtils.htmlEscape(code);
  }
  public void setCodeEscape(String codeEscape) {
    this.codeEscape = codeEscape;
  }
  public String getComment() {
    return comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }
}
