package com.ganeshgc.socialmediablog_app.dto;

import com.ganeshgc.socialmediablog_app.model.PostEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class CommentDto {
    private long id;
    @NotEmpty
    @Size(min = 5, message = "user name must be more than 5 character")
    private String userName;
    @Email
    @NotNull(message="email should have more than 10 character")
    private String email;
    @NotEmpty
    @Size(min = 10, message = "body must be munimum of 10 characters")
    private String body;
}
