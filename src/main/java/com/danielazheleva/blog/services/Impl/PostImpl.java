package com.danielazheleva.blog.services.Impl;

import com.danielazheleva.blog.repository.PostRepo;
import com.danielazheleva.blog.services.PostsService;
import com.danielazheleva.blog.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostImpl implements PostsService {

    @Autowired
    private PostRepo postRepo;

    public List<Post> allPosts(){
        return postRepo.findAll();
    }
}
