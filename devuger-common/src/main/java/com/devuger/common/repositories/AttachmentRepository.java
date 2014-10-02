package com.devuger.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devuger.common.entities.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}