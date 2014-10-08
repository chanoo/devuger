package com.devuger.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devuger.common.entities.FeedReport;

@Repository
public interface FeedReportRepository extends JpaRepository<FeedReport, Long> {
}