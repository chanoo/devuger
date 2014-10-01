package com.devuger.common.dto;

public class OpenGraph {
	private String title;
	private String siteName = "WIMITT";
	private String image;
	private String description;
	private String message;
	private String result = "success";
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
  public String getResult() {
    return result;
  }
  public void setResult(String result) {
    this.result = result;
  }
}
