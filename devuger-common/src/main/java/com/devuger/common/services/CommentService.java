package com.devuger.common.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.devuger.common.entities.Comment;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseService;
import com.devuger.common.support.constant.GlobalConst;

/**
 * 코멘트 관련 서비스
 *  - 코멘트 1개 가져오기
 *  - 코멘트 추가
 *  - 코멘트 삭제
 *  - 모든 코멘트 가져오기
 * 
 * @author hello
 *
 */
@Service
public class CommentService extends BaseService {

  /**
   * 코멘트 1개 가져오기
   * 
   * @param id PK
   * @return
   */
  public Comment get(Long id)
  {
    Assert.notNull(id, "코멘트를 선택해주세요.");

    return commentRepository.findOne(id);
  }
  
	/**
	 * 코멘트 추가
	 * 
	 * @param comment
	 * @return
	 */
	public Comment add(Comment comment, User user) {
		
    Assert.notNull(user, "로그인 해주세요.");
		Assert.notNull(comment, "코멘트를 작성해주세요. (1)");
		Assert.notNull(comment.getFeed(), "피드를 선택 해주세요.");
    Assert.hasText(comment.getContent(), "코멘트 내용을 입력해주세요.");

		comment.setCreatedBy(user);
		
		return commentRepository.save(comment);
	}

	/**
	 * 코멘트 삭제
	 * 
	 * @param id 코멘트 PK
	 * @param user 
	 */
  public void remove(Long id, User user) {
    // TODO Auto-generated method stub
    Assert.notNull(id, "삭제할 코멘트를 선택해주세요.");

    Comment comment = this.get(id);
    Assert.notNull(comment, "이미 삭제된 코멘트입니다.");
    Assert.isTrue(user.equals(comment.getCreatedBy()), "코멘트 삭제 권한이 없습니다.");

    commentRepository.delete(id);
  }

  /**
   * 모든 코멘트 가져오기
   * 
   * @return
   */
  public List<Comment> getAll() {
    // TODO Auto-generated method stub
    return commentRepository.findAll();
  }
  
  /**
   * 사용자별 코멘트 정보 가져오기
   * 
   * @param createdBy
   * @param page
   * @return
   */
  public Page<Comment> getByUser(User createdBy, int page) {
    
    Order order = new Order(Direction.DESC, "id");
    Sort sort = new Sort(order);
    Pageable pageable = new PageRequest(page - 1, GlobalConst.PAGE_SIZE, sort);
    
    return commentRepository.findByCreatedBy(createdBy, pageable);
  }

}