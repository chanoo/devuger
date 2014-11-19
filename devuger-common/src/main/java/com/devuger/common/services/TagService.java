package com.devuger.common.services;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.devuger.common.entities.Tag;
import com.devuger.common.support.base.BaseService;

/**
 * 태그 서비스
 * 
 * @author hello
 *
 */
@Service
public class TagService extends BaseService {

  /**
   * 태그 가져오기
   * 
   * @param id
   * @return
   */
  public Tag get(Long id) {
    Assert.notNull(id, "태그를 선택해주세요.");
    return tagRepository.findOne(id);
  }
}
