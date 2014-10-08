package com.devuger.common.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="feeds")
@JsonInclude(Include.NON_NULL)
public class Feed extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7655117641213144664L;
  
  @Column(nullable=true, length=255)
  private String title; // 제목
  @Column(nullable=true, columnDefinition="TEXT")
  private String message; // 메시지
  @Column(nullable=false)
  private int likeCount = 0; // 좋아요수
  @OneToMany(mappedBy="feed", fetch=FetchType.EAGER)
  private List<Comment> comments;
  @OneToMany(mappedBy="feed", fetch=FetchType.LAZY)
  private List<FeedReport> feedReports;
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public int getLikeCount() {
    return likeCount;
  }
  public void setLikeCount(int likeCount) {
    this.likeCount = likeCount;
  }
  public List<Comment> getComments() {
    return comments;
  }
  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }
}
