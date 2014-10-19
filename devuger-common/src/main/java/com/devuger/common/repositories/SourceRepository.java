package com.devuger.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devuger.common.entities.Source;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {
}