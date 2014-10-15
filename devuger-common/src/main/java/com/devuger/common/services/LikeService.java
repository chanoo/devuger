package com.devuger.common.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.devuger.common.entities.Feed;
import com.devuger.common.entities.Like;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseService;

@Service
public class LikeService extends BaseService {

  /**
   * 좋아요 추가
   * 
   * @param feed 피드
   * @param createdBy 생성자
   * @return
   */
  public Like add(Feed feed, User createdBy)
  {
    Assert.notNull(feed, "피드를 선택해주세요..");
    Assert.notNull(createdBy, "로그인 해주세요.");

    // 이미 추가된 LIKE인지 확인
    Like like = likeRepository.findByFeedAndCreatedBy(feed, createdBy);
    Assert.isNull(like, "벌써 좋아요 했어요!");

    like = new Like();
    like.setFeed(feed);
    like.setCreatedBy(createdBy);
    
    return likeRepository.save(like);
  }
  
  /**
   * 피드에 해당하는 좋아요 리스트 가져오기
   * 
   * @param feed 피드
   * @return
   */
  public List<Like> getByFeed(Feed feed) {
    Assert.notNull(feed, "피드를 선택해주세요.");
    return likeRepository.findByFeed(feed);
  }
}
