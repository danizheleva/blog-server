package com.danielazheleva.blog.services;

import entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostsService {

    List<Post> allPosts();

}
