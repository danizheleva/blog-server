package com.danielazheleva.blog.services;

import com.danielazheleva.blog.shared.TripDto;
import com.danielazheleva.blog.entity.TripEntity;

import java.util.List;

public interface PostsService {

    List<TripDto> getAllPosts();

    TripDto savePost(TripDto post);

}
