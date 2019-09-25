package com.danielazheleva.blog.controllers;

import com.danielazheleva.blog.model.request.TripDetailRequestModel;
import com.danielazheleva.blog.services.PostsService;
import entity.TripEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @GetMapping
    public List<TripEntity> getAllPosts(){
        return postsService.allPosts();
    }

    @PostMapping
    public void createNewPost(@RequestBody TripDetailRequestModel postDetail){
        postsService.savePost(postDetail);
    }

    @PutMapping(name = "/post/{id}")
    public String editPost(){
        return "This will edit an existing post";
    }

    @DeleteMapping(name = "/post/{id}")
    public String deletePost(){
        return "This will delete a given post";
    }
}
