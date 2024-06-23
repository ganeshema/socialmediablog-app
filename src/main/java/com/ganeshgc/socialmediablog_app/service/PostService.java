package com.ganeshgc.socialmediablog_app.service;

import com.ganeshgc.socialmediablog_app.dto.PostDto;
import com.ganeshgc.socialmediablog_app.payload.PostResponse;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();
    PostResponse getAllPosts(int pageNo, int pageSize);
    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortOrder);

    PostDto getPostById(long id);

    PostDto createPost(PostDto postDto);

    PostDto updatePost(PostDto postDto, long postId);

    void deletePostById(long id);

}