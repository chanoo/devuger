package com.devuger.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devuger.common.entities.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}