package com.ganeshgc.socialmediablog_app.service;

import com.ganeshgc.socialmediablog_app.dto.CommentDto;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getAllComments(long postId);
    CommentDto getCommentsByPostIdAndPostId(long postId, long commentId);
    CommentDto updateCommentByPostIdAndCommentId(long postId, long commentId, CommentDto commentDto);
    void deleteCommentByPostIdAndCommentId(long postId, long commentId);
    void deleteCommentsByPostId(long postId);
    CommentDto updateCommentByPostIdAndCommentIdUisingJsonPatch(long postId, long commentId, JsonPatch jsonPatch);
}
