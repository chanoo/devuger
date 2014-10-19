package com.devuger.common.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="open_graphs")
@JsonInclude(Include.NON_EMPTY)
public class OpenGraph extends AbstractEntity implements Serializable {
	/**
   * 
   */
  private static final long serialVersionUID = 8994162196449867097L;

  @ManyToOne
  @JoinColumn(name = "feed", nullable = false)
  private Feed feed;
  private String title;
	private String siteName;
	private String image;
	private String description;
	private String message;
	private String url;
	public Feed getFeed() {
    return feed;
  }
  public void setFeed(Feed feed) {
    this.feed = feed;
  }
  public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
}
