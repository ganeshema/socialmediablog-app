package com.ganeshgc.socialmediablog_app.controller;

import com.ganeshgc.socialmediablog_app.dto.PostDto;
import com.ganeshgc.socialmediablog_app.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/posts")
public class PostController {

    @Autowired
    private PostService postService;


    //GET /v1/api/posts
    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }


    //POST /v1/api/posts
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PostDto> createPost(@RequestBody @Valid PostDto postDto) {
        PostDto savedPostDto = postService.createPost(postDto);
        return new ResponseEntity<>(savedPostDto, HttpStatus.CREATED);
    }

    //GET /v1/api/posts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id) {
        PostDto postDto =  postService.getPostById(id);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    //PUT /v1/api/posts/{id}
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PostDto> updatePostById(@PathVariable  long id,@RequestBody @Valid PostDto postDto) {
        PostDto updatedPost = postService.updatePost(postDto, id);
        return ResponseEntity.ok(updatedPost);
    }


    //DELETE /v1/api/posts/{id}

    ///v1/api/posts?id=1
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deletePostById(@PathVariable long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Deleted Successfully Post Resource::"+ id, HttpStatus.OK);
    }
}