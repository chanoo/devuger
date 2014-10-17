package com.devuger.common.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.devuger.common.entities.Feed;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseService;
import com.devuger.common.support.constant.GlobalConst;

@Service
public class FeedService extends BaseService {

  /**
   * 피드 1개 가져오기
   * 
   * @param id
   * @return
   */
  public Feed get(Long id)
  {
    Assert.notNull(id, "피드를 선택 해주세요.");
    return feedRepository.findOne(id);
  }

  /**
   * 피드 추가하기
   * 
   * @param feed
   * @param user
   * @return
   */
  public Feed add(Feed feed, User createdBy)
  {
    Assert.notNull(feed, "메시지를 입력 해주세요.");
    Assert.notNull(feed.getMessage(), "메시지를 입력 해주세요.");
    Assert.notNull(createdBy, "로그인 해주세요.");
    
    feed.setCreatedBy(createdBy);

    return feedRepository.save(feed);
  }

  /**
   * 사용자별 피드 가져오기
   * 
   * @param user
   * @return
   */
  public Page<Feed> getByUser(User user, int page) {
    // TODO Auto-generated method stub
    Assert.notNull(user, "로그인 해주세요.");
    
    Order order = new Order(Direction.DESC, "id");
    Sort sort = new Sort(order);
    Pageable pageable = new PageRequest(page - 1, GlobalConst.PAGE_SIZE, sort);
    
    return feedRepository.findByCreatedBy(user, pageable);
  }
  
  /**
   * 모든 피드 정보 가져오기
   * 
   * @param user
   * @param page
   * @return
   */
  public Page<Feed> getAll(int page) {
    // TODO Auto-generated method stub
    Order order = new Order(Direction.DESC, "id");
    Sort sort = new Sort(order);
    Pageable pageable = new PageRequest(page - 1, GlobalConst.PAGE_SIZE, sort);
    return feedRepository.findAll(pageable);
  }

  /**
   * 피드 삭제
   * 
   * @param id
   * @param user
   */
  public void remove(Long id, User user) {
    // TODO Auto-generated method stub
    Assert.notNull(id, "피드를 선택해주세요.");
    Assert.notNull(user, "로그인 해주세요.");
    Feed feed = get(id);
    Assert.notNull(feed, "삭제된 피드입니다.");
    Assert.isTrue(feed.getCreatedBy().equals(user), "삭제할 권한이 없습니다.");
    
    feedRepository.delete(id);
  }
}
