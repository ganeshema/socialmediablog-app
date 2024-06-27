package com.ganeshgc.socialmediablog_app.service.impl;

import com.ganeshgc.socialmediablog_app.dto.CommentDto;
import com.ganeshgc.socialmediablog_app.exception.ResourceNotFoundException;
import com.ganeshgc.socialmediablog_app.model.CommentEntity;
import com.ganeshgc.socialmediablog_app.model.PostEntity;
import com.ganeshgc.socialmediablog_app.repository.CommentRepository;
import com.ganeshgc.socialmediablog_app.repository.PostRepository;
import com.ganeshgc.socialmediablog_app.service.CommentService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<CommentEntity> comments=commentRepository.findByPostId(postId);
        List<CommentDto> commentDtos=new ArrayList<>();
        for(CommentEntity commentEntity:comments){
            CommentDto commentDto=new CommentDto();
            BeanUtils.copyProperties(commentEntity,commentDto);
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }

    @Override
    public CommentDto getCommentsByPostIdAndPostId(long postId, long commentId) {
        CommentEntity commentEntity=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Post","id",String.valueOf(commentId)));
        PostEntity postEntity=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",String.valueOf(postId)));
        if(commentEntity.getPostEntity().getId()==postEntity.getId()){
            CommentDto commentDto=new CommentDto();
            BeanUtils.copyProperties(commentEntity,commentDto);
            return commentDto;
        }
        return null;
    }

    @Override
    public CommentDto updateCommentByPostIdAndCommentId(long postId, long commentId, CommentDto commentDto) {
        CommentEntity commentEntity=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Post","id",String.valueOf(commentId)));
        PostEntity postEntity=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",String.valueOf(postId)));
        if(commentEntity.getPostEntity().getId()==postEntity.getId()){
            BeanUtils.copyProperties(commentDto,commentEntity);
            commentRepository.save(commentEntity);
            return commentDto;
        }
        return null;
    }
    @Override
    public void deleteCommentByPostIdAndCommentId(long postId, long commentId) {
        CommentEntity commentEntity=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Post","id",String.valueOf(commentId)));
        PostEntity postEntity=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",String.valueOf(postId)));
        if(commentEntity.getPostEntity().getId()==postEntity.getId()){
            commentRepository.delete(commentEntity);
        }
    }

    @Override
    public void deleteCommentsByPostId(long postId) {
        PostEntity postEntity=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",String.valueOf(postId)));
        List<CommentEntity> commentEntities=commentRepository.findByPostId(postId);
        commentRepository.deleteAll(commentEntities);
    }

    @Override
    public CommentDto updateCommentByPostIdAndCommentIdUisingJsonPatch(long postId, long commentId, JsonPatch jsonPatch) {
        PostEntity postEntity=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",String.valueOf(postId)));
        CommentEntity commentEntity=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Post","id",String.valueOf(commentId)));
        if(!commentEntity.getPostEntity().getId().equals(postEntity.getId())){
            throw new RuntimeException("Bad Request: Comment Not found");
        }else{
            BeanUtils.copyProperties(jsonPatch,commentEntity);
            commentRepository.save(commentEntity);
            CommentDto commentDto=new CommentDto();
            BeanUtils.copyProperties(commentEntity,commentDto);
            return commentDto;
        }

    }


}
