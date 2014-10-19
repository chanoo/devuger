package com.devuger.common.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 피드 테이블
 * 
 * @author hello
 *
 */
@Entity
@Table(name="feeds")
@JsonInclude(Include.NON_NULL)
public class Feed extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7655117641213144664L;
  
  @Column(nullable=false, columnDefinition="TEXT")
  private String message; // 메시지
  @JsonIgnoreProperties({ "feed" })
  @OneToMany(mappedBy="feed", fetch=FetchType.EAGER)
  private List<Comment> comments;
  @JsonIgnoreProperties({ "feed" })
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy="feed", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
  private List<Like> likes;
  @JsonIgnoreProperties({ "feed" })
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy="feed", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
  private List<FeedReport> feedReports;
  @JsonIgnoreProperties({ "feed" })
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy="feed", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
  private List<Source> sources;
  @JsonIgnoreProperties({ "feed" })
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy="feed", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
  private List<OpenGraph> openGraphs;
  @JsonIgnoreProperties({ "feed" })
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy="feed", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
  private List<Attachment> attachments;
  @Transient
  private boolean liked = false;
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = HtmlUtils.htmlEscape(message);
  }
  public List<Comment> getComments() {
    return comments;
  }
  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }
  public List<Like> getLikes() {
    return likes;
  }
  public void setLikes(List<Like> likes) {
    this.likes = likes;
  }
  public List<FeedReport> getFeedReports() {
    return feedReports;
  }
  public void setFeedReports(List<FeedReport> feedReports) {
    this.feedReports = feedReports;
  }
  public boolean isLiked() {
    return liked;
  }
  public void setLiked(boolean liked) {
    this.liked = liked;
  }
  public List<Source> getSources() {
    return sources;
  }
  public void setSources(List<Source> sources) {
    this.sources = sources;
  }
  public List<OpenGraph> getOpenGraphs() {
    return openGraphs;
  }
  public void setOpenGraphs(List<OpenGraph> openGraphs) {
    this.openGraphs = openGraphs;
  }
  public List<Attachment> getAttachments() {
    return attachments;
  }
  public void setAttachments(List<Attachment> attachments) {
    this.attachments = attachments;
  }
}
