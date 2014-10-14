package com.devuger.common.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
  @Column(nullable=false, length=100)
  private String name;
  @ManyToMany
  @JoinColumn(name="follow")
  private List<Follow> follows;

  public List<Follow> getFollows() {
    return follows;
  }
  public void setFollows(List<Follow> follows) {
    this.follows = follows;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
}
