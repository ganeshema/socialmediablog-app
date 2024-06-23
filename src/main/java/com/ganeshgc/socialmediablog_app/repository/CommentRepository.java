package com.ganeshgc.socialmediablog_app.repository;

import com.ganeshgc.socialmediablog_app.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
