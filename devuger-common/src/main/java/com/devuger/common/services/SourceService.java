package com.devuger.common.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.devuger.common.entities.Feed;
import com.devuger.common.entities.Source;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseService;

/**
 * 피드의 소스코드 입력 기능
 * 
 * @author hello
 *
 */
@Service
public class SourceService extends BaseService {

  /**
   * 배열로 코드 추가
   * @param feed 
   * 
   * @param codes
   * @param comments
   * @param createdBy
   * @return
   */
  public List<Source> adds(Feed feed, String[] codes, String[] comments, User createdBy) {
    Assert.isTrue(codes.length == comments.length, "코드 정보가 올바르지 않습니다.");
    int length = codes.length;
    if (length == 0)
      return null;

    List<Source> sources = new ArrayList<Source>();
    for(int i = 0; i < length; i++) {
      Assert.hasText(codes[i], "코드를 입력해주세요.");
      Assert.hasText(comments[i], "코드 코멘트를 입력해주세요.");
      Source source = new Source();
      source.setFeed(feed);
      source.setCode(codes[i]);
      source.setComment(comments[i]);
      source = add(source, createdBy);
      sources.add(source);
    }
    
    return sources;
  }
  
  /**
   * 소드 코드 추가
   * 
   * @param source
   * @param createdBy
   * @return
   */
  public Source add(Source source, User createdBy) {
    Assert.notNull(source, "소스코드를 입력해주세요.");
    source.setCreatedBy(createdBy);
    return sourceRepository.save(source);
  }
}
