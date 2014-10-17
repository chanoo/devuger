package com.devuger.common.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devuger.common.entities.Comment;
import com.devuger.common.entities.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  Page<Comment> findByCreatedBy(User createdBy, Pageable pageable);
}