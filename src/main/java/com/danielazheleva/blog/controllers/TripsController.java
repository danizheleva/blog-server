package com.danielazheleva.blog.controllers;

import com.danielazheleva.blog.models.request.DayRequestModel;
import com.danielazheleva.blog.models.responce.DayRest;
import com.danielazheleva.blog.models.responce.TripRest;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/trips")
public class TripsController {

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(TripServiceImpl.class);

    @Autowired
    private TripService tripService;

    @Autowired
    private DayService dayService;

    private final ModelMapper mm = new ModelMapper();

    // GET ALL TRIPS
    @GetMapping
    List<TripRest> getAllTrips(){
        List<TripDto> tripDtoList = tripService.getAllTrips();
        return mm.map(tripDtoList, new TypeToken<List<TripRest>>(){}.getType());
    }

    // CREATE NEW TRIP
    @PostMapping
    TripRest createNewTrip(@RequestBody TripDetailRequestModel tripDetailRequestModel){
        LOG.info("received data: {}", tripDetailRequestModel);
        TripDto createdTrip = tripService.saveTrip(tripDetailRequestModel);
        return mm.map(createdTrip, TripRest.class);
    }

    // GET ONE TRIP
    @GetMapping("/{id}")
    TripRest getTrip(@PathVariable Long id){
        TripDto foundTrip = tripService.getTrip(id);

        return mm.map(foundTrip, TripRest.class);
    }

    // EDIT TRIP DETAILS
    @PutMapping("/{tripId}")
    TripRest editTripDetails(@RequestBody TripDetailRequestModel newTripDetails,
                                    @PathVariable Long tripId) {

        TripDto newTrip = tripService.editTripDetails(newTripDetails, tripId);

        return mm.map(newTrip, TripRest.class);

    }

    // DELETE ONE TRIP
    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
    }

    // GET ALL DAYS FOR ONE TRIP
    @GetMapping("/{tripId}/days")
    List<DayRest> getDaysOfTrip(@PathVariable Long tripId) {

        List<DayDto> dayDto = dayService.getAllDaysForTrip(tripId);

        Type listType = new TypeToken<List<DayRest>>() {}.getType();
        return mm.map(dayDto, listType);

    }

    // GET ONE DAY OF ONE TRIP
    @GetMapping("/{tripId}/days/{dayId}")
    public DayRest getDay(@PathVariable Long dayId) {

        DayDto dayDto = dayService.getDay(dayId);

        return mm.map(dayDto, DayRest.class);
    }

    // CREATE DAY
    @PostMapping("/{tripId}/days")
    TripRest createDay(@RequestBody DayRequestModel day,
                              @PathVariable Long tripId) {

        TripDto tripWithNewDay = dayService.createDay(day, tripId);

        return mm.map(tripWithNewDay, TripRest.class);
    }

    // EDIT DAY
    @PutMapping("/{tripId}/days/{dayId}")
    DayRest editDay(@RequestBody DayRequestModel newDay,
                           @PathVariable Long tripId,
                           @PathVariable Long dayId) {

        DayDto editedDay = dayService.editDay(newDay, tripId, dayId);

        return mm.map(editedDay, DayRest.class);
    }

    // DELETE ONE DAY OF ONE TRIP
    @DeleteMapping("/{tripId}/days/{dayId}")
    public String deleteDay(@PathVariable Long dayId) {

        dayService.deleteDay(dayId);

        return "day deleted";
    }
}
