package com.ganeshgc.socialmediablog_app.dto;

import com.ganeshgc.socialmediablog_app.model.PostEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    private String userName;
    private String email;
    private String body;
}
