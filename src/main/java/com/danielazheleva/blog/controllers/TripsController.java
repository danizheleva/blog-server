package com.danielazheleva.blog.controllers;

import com.danielazheleva.blog.models.responce.TripRest;
import com.danielazheleva.blog.shared.DayDto;
import com.danielazheleva.blog.shared.TripDto;
import com.danielazheleva.blog.models.request.TripDetailRequestModel;
import com.danielazheleva.blog.services.PostsService;
import com.danielazheleva.blog.entity.TripEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trips")
public class TripsController {

    @Autowired
    private PostsService postsService;

    private final ModelMapper mm = new ModelMapper();

    @GetMapping
    public List<TripRest> getAllTrips(){
        List<TripDto> tripDtoList = postsService.getAllPosts();
        return mm.map(tripDtoList, new TypeToken<List<TripRest>>(){}.getType());
    }

    @PostMapping
    public TripRest createNewTrip(@RequestBody TripDetailRequestModel tripDetailRequestModel){

        ModelMapper modelMapper = new ModelMapper();
        TripDto tripDto = modelMapper.map(tripDetailRequestModel, TripDto.class);

        TripDto createdTrip = postsService.savePost(tripDto);
        return modelMapper.map(createdTrip, TripRest.class);
    }

//    @PutMapping(name = "/{id}")
//    public String editPost(){
//        return "This will edit an existing post";
//    }

//    @DeleteMapping(name = "{id}")
//    public String deletePost(){
//        return "This will delete a given post";
//    }
}
