package com.devuger.common.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 좋아요 테이블
 * 
 * @author hello
 *
 */
@Entity
@Table(name="likes",
  uniqueConstraints = {@UniqueConstraint(columnNames = { "feed", "createdBy" })}
)
@JsonInclude(Include.NON_NULL)
public class Like extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -1907218816750055462L;

  @ManyToOne(cascade=CascadeType.MERGE)
  @JoinColumn(name = "feed", nullable = false)
  private Feed feed;
  public Feed getFeed() {
    return feed;
  }
  public void setFeed(Feed feed) {
    this.feed = feed;
  }
}
