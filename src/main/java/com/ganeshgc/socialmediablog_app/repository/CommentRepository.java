package com.ganeshgc.socialmediablog_app.repository;

import com.ganeshgc.socialmediablog_app.dto.CommentDto;
import com.ganeshgc.socialmediablog_app.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query(value="SELECT * FROM comments WHERE post_id = ?", nativeQuery=true)

    List<CommentEntity> findByPostId(Long postId);

}
