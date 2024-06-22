package com.ganeshgc.socialmediablog_app.service.impl;

import com.ganeshgc.socialmediablog_app.dto.PostDto;
import com.ganeshgc.socialmediablog_app.exception.ResourceNotFoundException;
import com.ganeshgc.socialmediablog_app.model.PostEntity;
import com.ganeshgc.socialmediablog_app.repository.PostRepository;
import com.ganeshgc.socialmediablog_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<PostDto> getAllPosts() {

        List<PostEntity> postEntities = postRepository.findAll();

        //Map/ convert PostEntity to PostDto
        if(postEntities != null) {
            return postEntities.stream().map(postEntity -> mapEntityToDto(postEntity)).collect(Collectors.toList());
        }
        return null;
    }


    @Override
    public PostDto getPostById(long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id", String.valueOf(postId)));
        return mapEntityToDto(postEntity);
    }

    @Override
    public PostDto createPost(PostDto inPostDto) {
        PostEntity postEntity = mapDtoToEntity(inPostDto);

        PostEntity savedPostEntity = postRepository.save(postEntity);
        PostDto outPostDto =  mapEntityToDto(savedPostEntity);
        return outPostDto;
    }


    @Override
    public PostDto updatePost(PostDto postDto, long postId) {

        //find resource by Id
        PostEntity postEntityToBeUpdated =  postRepository.findById(postId).orElseThrow(() ->  new ResourceNotFoundException("Post","id", String.valueOf(postId)));

        //map the updated resource
        postEntityToBeUpdated.setTitle(postDto.getTitle());
        postEntityToBeUpdated.setDescription(postDto.getDescription());
        postEntityToBeUpdated.setContent(postDto.getContent());

        //save into database
        PostEntity updatedPostEntity = postRepository.save(postEntityToBeUpdated);

        //convert entity to dto and return the dto
        return mapEntityToDto(updatedPostEntity);
    }

    @Override
    public void deletePostById(long postId) {
        PostEntity postEntityToBeDeleted =  postRepository.findById(postId).orElseThrow(() ->  new ResourceNotFoundException("Post","id", String.valueOf(postId)));
        postRepository.delete(postEntityToBeDeleted);
    }



    private PostDto mapEntityToDto(PostEntity postEntity) {
        PostDto postDto = new PostDto();
        postDto.setId(postEntity.getId());
        postDto.setTitle(postEntity.getTitle());
        postDto.setDescription(postEntity.getDescription());
        postDto.setContent(postEntity.getContent());
        return postDto;
    }


    private PostEntity mapDtoToEntity(PostDto postDto) {
        PostEntity postEntity = new PostEntity();
        postEntity.setDescription(postDto.getDescription());
        postEntity.setTitle(postDto.getTitle());
        postEntity.setContent(postDto.getContent());
        return postEntity;
    }



}