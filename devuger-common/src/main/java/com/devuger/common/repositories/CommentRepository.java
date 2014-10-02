package com.devuger.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devuger.common.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}