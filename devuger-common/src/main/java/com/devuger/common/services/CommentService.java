package com.devuger.common.services;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.devuger.common.entities.Comment;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseService;

@Service
public class CommentService extends BaseService {

	/**
	 * 코멘트 추가
	 * 
	 * @param comment
	 * @return
	 */
	public Comment add(Comment comment, User user) {
		
		Assert.notNull(comment, "댓글을 작성해주세요. (1)");
		Assert.notNull(comment.getFeed(), "피드를 선택 해주세요.");
		Assert.hasText(comment.getContent(), "코멘트 내용을 입력해주세요.");

		comment.setCreatedBy(user);
		
		return commentRepository.save(comment);
	}

}