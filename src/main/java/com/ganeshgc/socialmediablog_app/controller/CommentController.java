package com.ganeshgc.socialmediablog_app.controller;

import com.ganeshgc.socialmediablog_app.dto.CommentDto;
import com.ganeshgc.socialmediablog_app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable long postId) {
        List<CommentDto> commentDtoList=commentService.getAllComments(postId);
        return  new ResponseEntity<>(commentDtoList, HttpStatus.FOUND);

    }
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentByCommentIdAndPostId(@PathVariable long postId, @PathVariable long commentId) {
        CommentDto commentDto=commentService.getCommentsByPostIdAndPostId(postId, commentId);
        return new ResponseEntity(commentDto, HttpStatus.FOUND);
    }
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long postId, @PathVariable long commentId, @RequestBody CommentDto commentDto) {
        CommentDto commentDto1=commentService.updateCommentByPostIdAndCommentId(postId,commentId,commentDto);
        return new ResponseEntity(commentDto1, HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable long postId, @PathVariable long commentId) {
        commentService.deleteCommentByPostIdAndCommentId(postId, commentId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments")
    public ResponseEntity deleteCommentsByPostId(@PathVariable long postId) {
        commentService.deleteCommentsByPostId(postId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
