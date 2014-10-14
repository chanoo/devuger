package com.devuger.common.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 팔로우, 팔로윙 테이블
 * 
 * @author hello
 *
 */
@Entity
@Table(name="follow")
@JsonInclude(Include.NON_NULL)
public class Follow extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7655117641213144664L;

  @ManyToMany
  @JoinColumn(name="tag")
  private List<Tag> tags;

  public List<Tag> getTags() {
    return tags;
  }
  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }
}
