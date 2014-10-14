package com.devuger.common.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 좋아요 테이블
 * 
 * @author hello
 *
 */
@Entity
@Table(name="likes")
@JsonInclude(Include.NON_NULL)
public class Like extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -1907218816750055462L;

}
