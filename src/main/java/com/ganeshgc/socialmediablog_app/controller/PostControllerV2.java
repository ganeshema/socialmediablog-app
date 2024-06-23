package com.ganeshgc.socialmediablog_app.controller;

import com.ganeshgc.socialmediablog_app.dto.PostDto;
import com.ganeshgc.socialmediablog_app.payload.PostResponse;
import com.ganeshgc.socialmediablog_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2/api/posts")
public class PostControllerV2 {
    @Autowired
    private PostService postService;

    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize) {
        return postService.getAllPosts(pageNo,pageSize);
    }

}
