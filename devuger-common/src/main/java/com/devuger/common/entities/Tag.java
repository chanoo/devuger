package com.devuger.common.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="tags")
@JsonInclude(Include.NON_NULL)
public class Tag extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 5300882310523920563L;
  @JsonIgnoreProperties({ "tag" })
  @OneToMany(mappedBy="tag", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
  private List<Feed> feeds;
  @Column(nullable=false, length=100)
  private String name;
  @Column(nullable=false, length=100)
  private String type;
  public List<Feed> getFeeds() {
    return feeds;
  }
  public void setFeeds(List<Feed> feeds) {
    this.feeds = feeds;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
}
