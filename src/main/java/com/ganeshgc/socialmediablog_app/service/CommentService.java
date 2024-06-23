package com.ganeshgc.socialmediablog_app.service;

import com.ganeshgc.socialmediablog_app.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getAllComments(long postId);
    CommentDto getCommentsByPostIdAndPostId(long postId, long commentId);
}
