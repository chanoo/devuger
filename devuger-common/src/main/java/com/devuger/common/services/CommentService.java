package com.devuger.common.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.devuger.common.entities.Comment;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseService;

/**
 * 코멘트 관련 서비스
 * 
 * @author hello
 *
 */
@Service
public class CommentService extends BaseService {

  /**
   * 코멘트 가져오기
   * 
   * @param id
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

  public List<Comment> getAll() {
    // TODO Auto-generated method stub
    return commentRepository.findAll();
  }

}