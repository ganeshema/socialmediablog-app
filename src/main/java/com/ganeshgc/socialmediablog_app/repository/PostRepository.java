package com.ganeshgc.socialmediablog_app.repository;


import com.ganeshgc.socialmediablog_app.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}