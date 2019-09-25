package com.danielazheleva.blog.services;

import com.danielazheleva.blog.model.request.TripDetailRequestModel;
import entity.TripEntity;

import java.util.List;

public interface PostsService {

    List<TripEntity> allPosts();

    void savePost(TripDetailRequestModel post);

}
