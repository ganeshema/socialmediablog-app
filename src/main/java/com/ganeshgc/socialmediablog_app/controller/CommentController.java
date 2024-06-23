package com.ganeshgc.socialmediablog_app.controller;

import com.ganeshgc.socialmediablog_app.dto.CommentDto;
import com.ganeshgc.socialmediablog_app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //POST / v1/api/{postId}/comments
    @PostMapping("/posts/{postId}/comments")
    public CommentDto createComment(@PathVariable long postId, @RequestBody CommentDto commentDto) {
        return commentService.createComment(postId, commentDto);

    }
}
