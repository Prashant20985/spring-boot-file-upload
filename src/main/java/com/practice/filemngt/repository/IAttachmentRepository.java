package com.practice.filemngt.repository;

import com.practice.filemngt.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttachmentRepository extends JpaRepository<Attachment, String> {
}
