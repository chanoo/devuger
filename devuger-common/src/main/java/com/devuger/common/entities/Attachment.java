package com.devuger.common.entities;

import java.io.InputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 첨부 파일 테이블
 * 
 * @author hello
 *
 */
@Entity
@Table(name="attachments")
@JsonInclude(Include.NON_EMPTY)
public class Attachment extends AbstractEntity implements Serializable {

	/**
   * 
   */
  private static final long serialVersionUID = -6407247111414717647L;
  @ManyToOne
  @JoinColumn(name = "feed", nullable = true)
  private Feed feed;
  private String name;
	private long size;
	private String contentType;
	private String digest;
	private int downloads;
	private int sort;
	@Column(nullable=true)
	private int width;
	@Column(nullable=true)
	private int height;
	@Column(nullable=false)
	private boolean active = false;
	@Transient
	@JsonIgnore
	private MultipartFile attachmentData;
	@Transient
	@JsonIgnore
	private InputStream inputStream;
	public Feed getFeed() {
    return feed;
  }
  public void setFeed(Feed feed) {
    this.feed = feed;
  }
  public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public int getDownloads() {
		return downloads;
	}
	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public MultipartFile getAttachmentData() {
		return attachmentData;
	}
	public void setAttachmentData(MultipartFile attachmentData) {
		this.attachmentData = attachmentData;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
