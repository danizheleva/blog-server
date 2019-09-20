package com.danielazheleva.blog.controllers;

import com.danielazheleva.blog.model.request.PostDetailRequestModel;
import com.danielazheleva.blog.services.PostsService;
import com.danielazheleva.blog.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @GetMapping
    public List<Post> getAllPosts(){
        //return "get route";
        return postsService.allPosts();
    }

    @PostMapping
    public String createNewPost(@RequestBody PostDetailRequestModel postDetail){
        return "This will create a new post";
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
