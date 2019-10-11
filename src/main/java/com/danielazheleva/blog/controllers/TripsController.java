package com.danielazheleva.blog.controllers;

import com.danielazheleva.blog.models.responce.TripRest;
import com.danielazheleva.blog.repository.TripRepository;
import com.danielazheleva.blog.shared.TripDto;
import com.danielazheleva.blog.models.request.TripDetailRequestModel;
import com.danielazheleva.blog.services.PostsService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class TripsController {

    @Autowired
    private PostsService postsService;

    @Autowired
    private TripRepository tripRepository;

    private final ModelMapper mm = new ModelMapper();

    @GetMapping("/trips")
    public List<TripRest> getAllTrips(){
        List<TripDto> tripDtoList = postsService.getAllTrips();
        return mm.map(tripDtoList, new TypeToken<List<TripRest>>(){}.getType());
    }

    @PostMapping("trips")
    public TripRest createNewTrip(@RequestBody TripDetailRequestModel tripDetailRequestModel){

        TripDto tripDto = mm.map(tripDetailRequestModel, TripDto.class);

        TripDto createdTrip = postsService.saveTrip(tripDto);
        return mm.map(createdTrip, TripRest.class);
    }

    @GetMapping("/trips/{id}")
    public TripRest getTrip(@PathVariable Long id){
        TripDto foundTrip = postsService.getTrip(id);

        return mm.map(foundTrip, TripRest.class);
    }

//    @DeleteMapping("/testDelete/{id}")
//    public String deletePost(@PathVariable Long id){
//
//        tripRepository.deleteById(id);
//
//     //   postsService.deleteTrip(id);
//
//   //     String toReturn = "trip with id" + id + "was deleted";
//
//        return "delete route" ;
//    }
}
