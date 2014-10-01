package com.devuger.common.entities;

import java.io.Serializable;

import javax.persistence.Entity;
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


}
