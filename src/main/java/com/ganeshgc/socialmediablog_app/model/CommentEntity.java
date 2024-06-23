package com.ganeshgc.socialmediablog_app.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;
@Data
@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String email;
    private String body;
    @ManyToOne
    @JoinColumn(name="post_id",nullable = false)
    private PostEntity postEntity;
}
