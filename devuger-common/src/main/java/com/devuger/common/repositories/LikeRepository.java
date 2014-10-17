package com.devuger.common.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devuger.common.entities.Feed;
import com.devuger.common.entities.Like;
import com.devuger.common.entities.User;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

  Like findByFeedAndCreatedBy(Feed feed, User createdBy);

  List<Like> findByFeed(Feed feed);

  List<Like> findByCreatedByAndFeedIn(User createdBy, List<Feed> feeds);
}