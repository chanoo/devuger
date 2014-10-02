package com.devuger.common.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.devuger.common.entities.Feed;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseService;

@Service
public class FeedService extends BaseService {

  /**
   * 피드 추가하기
   * 
   * @param feed
   * @param user
   * @return
   */
  public Feed add(Feed feed, User user)
  {
    Assert.notNull(feed, "메시지를 입력 해주세요.");
    Assert.notNull(user, "로그인 해주세요.");

    return feedRepository.save(feed);
  }

  /**
   * 사용자별 피드 가져오기
   * 
   * @param user
   * @return
   */
  public List<Feed> getByUser(User user) {
    // TODO Auto-generated method stub
    Assert.notNull(user, "로그인 해주세요.");
    
    return feedRepository.findByCreatedBy(user);
  }
  
}
