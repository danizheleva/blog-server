package com.danielazheleva.blog.controllers;

import com.danielazheleva.blog.shared.DayDto;
import com.danielazheleva.blog.shared.TripDto;
import com.danielazheleva.blog.models.request.TripDetailRequestModel;
import com.danielazheleva.blog.services.PostsService;
import com.danielazheleva.blog.entity.TripEntity;
import org.modelmapper.ModelMapper;
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
        return postsService.getAllPosts();
    }

    @PostMapping
    public TripDto createNewPost(@RequestBody TripDetailRequestModel tripDetailRequestModel){

        ModelMapper modelMapper = new ModelMapper();
        TripDto tripDto = modelMapper.map(tripDetailRequestModel, TripDto.class);

        TripDto createdTrip = postsService.savePost(tripDto);
        return createdTrip;
    }

//    @PutMapping(name = "{id}")
//    public String editPost(){
//        return "This will edit an existing post";
//    }
//
//    @DeleteMapping(name = "{id}")
//    public String deletePost(){
//        return "This will delete a given post";
//    }
}
