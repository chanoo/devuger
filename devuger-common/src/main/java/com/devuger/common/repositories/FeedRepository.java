package com.devuger.common.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devuger.common.entities.Feed;
import com.devuger.common.entities.User;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

  List<Feed> findByCreatedBy(User user, Pageable pageable);
}