package com.devuger.common.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devuger.common.entities.Feed;
import com.devuger.common.entities.Tag;
import com.devuger.common.entities.User;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

  Page<Feed> findByCreatedBy(User user, Pageable pageable);

  Page<Feed> findByTag(Tag tag, Pageable pageable);
}