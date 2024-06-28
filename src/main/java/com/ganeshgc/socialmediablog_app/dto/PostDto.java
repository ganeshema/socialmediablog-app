package com.ganeshgc.socialmediablog_app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {

    private Long id;

    @NotEmpty
    @Size(min = 5, message = "post title must be more than 5 character")
    private String title;
    @Size(min = 10, message = "post description should have more than 10 character")
    private String description;
    @NotEmpty
    private String content;

}