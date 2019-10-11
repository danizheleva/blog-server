package com.danielazheleva.blog.controllers;

import com.danielazheleva.blog.models.responce.DayRest;
import com.danielazheleva.blog.models.responce.TripRest;
import com.danielazheleva.blog.repository.TripRepository;
import com.danielazheleva.blog.services.DayService;
import com.danielazheleva.blog.services.Impl.TripServiceImpl;
import com.danielazheleva.blog.shared.DayDto;
import com.danielazheleva.blog.shared.TripDto;
import com.danielazheleva.blog.models.request.TripDetailRequestModel;
import com.danielazheleva.blog.services.TripService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping()
public class TripsController {

    public static org.slf4j.Logger LOG = LoggerFactory.getLogger(TripServiceImpl.class);

    @Autowired
    private TripService tripService;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private DayService dayService;

    private final ModelMapper mm = new ModelMapper();

    @GetMapping("/trips")
    public List<TripRest> getAllTrips(){
        List<TripDto> tripDtoList = tripService.getAllTrips();
        return mm.map(tripDtoList, new TypeToken<List<TripRest>>(){}.getType());
    }

    @PostMapping("/trips")
    public TripRest createNewTrip(@RequestBody TripDetailRequestModel tripDetailRequestModel){

        TripDto tripDto = mm.map(tripDetailRequestModel, TripDto.class);

        TripDto createdTrip = tripService.saveTrip(tripDto);
        return mm.map(createdTrip, TripRest.class);
    }

    @GetMapping("/trips/{id}")
    public TripRest getTrip(@PathVariable Long id){
        TripDto foundTrip = tripService.getTrip(id);

        return mm.map(foundTrip, TripRest.class);
    }

    @GetMapping("/trips/{tripId}/days")
    public List<DayRest> getDaysOfTrip(@PathVariable Long tripId) {

        List<DayDto> dayDto = dayService.getAllDaysForTrip(tripId);

        Type listType = new TypeToken<List<DayRest>>() {}.getType();
        List<DayRest> toReturn = mm.map(dayDto, listType);

        return toReturn;

    }

    @DeleteMapping("/trips/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
    }

//    @GetMapping("/trips/{tripId}/days/{dayId}")
//    public List<DayRest> getDay(@PathVariable Long tripId,
//                                @PathVariable Long dayId){
//
//        List<DayDto> dayDto = dayService.getAllDaysForTrip(tripId);
//
//        DayDto thisDayDto = dayService.getDay(dayDto);
//
//
//        Type listType = new TypeToken<List<DayRest>>() {}.getType();
//        List<DayRest> toReturn = mm.map(dayDto, listType);
//
//        return toReturn;
//
//    }
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
