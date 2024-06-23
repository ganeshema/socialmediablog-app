package com.ganeshgc.socialmediablog_app.service.impl;

import com.ganeshgc.socialmediablog_app.dto.CommentDto;
import com.ganeshgc.socialmediablog_app.exception.ResourceNotFoundException;
import com.ganeshgc.socialmediablog_app.model.CommentEntity;
import com.ganeshgc.socialmediablog_app.model.PostEntity;
import com.ganeshgc.socialmediablog_app.repository.CommentRepository;
import com.ganeshgc.socialmediablog_app.repository.PostRepository;
import com.ganeshgc.socialmediablog_app.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(commentDto, commentEntity);

       PostEntity postEntity= postRepository.findById(postId).orElseThrow(()->
               new ResourceNotFoundException("Post","id",String.valueOf(postId)));
       //set comment to particular post
       commentEntity.setPostEntity(postEntity);
       //save comment entity to db
        CommentEntity savedCommentEntity=commentRepository.save(commentEntity);
        //map comment entity to comment dto
        CommentDto updatedCommentDto=new CommentDto();
        BeanUtils.copyProperties(savedCommentEntity, updatedCommentDto);
        return updatedCommentDto;

    }

    @Override
    public List<CommentDto> getAllComments(long postId) {
        return List.of();
    }

    @Override
    public CommentDto getCommentsByPostIdAndPostId(long postId, long commentId) {
        return null;
    }
}
