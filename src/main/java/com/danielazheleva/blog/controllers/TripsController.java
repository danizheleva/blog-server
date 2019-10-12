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

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(TripServiceImpl.class);

    @Autowired
    private TripService tripService;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private DayService dayService;

    private final ModelMapper mm = new ModelMapper();

    // GET ALL TRIPS
    @GetMapping("/trips")
    public List<TripRest> getAllTrips(){
        List<TripDto> tripDtoList = tripService.getAllTrips();
        return mm.map(tripDtoList, new TypeToken<List<TripRest>>(){}.getType());
    }

    // CREATE NEW TRIP
    @PostMapping("/trips")
    public TripRest createNewTrip(@RequestBody TripDetailRequestModel tripDetailRequestModel){

        TripDto createdTrip = tripService.saveTrip(tripDetailRequestModel);
        return mm.map(createdTrip, TripRest.class);
    }

    // GET ONE TRIP
    @GetMapping("/trips/{id}")
    public TripRest getTrip(@PathVariable Long id){
        TripDto foundTrip = tripService.getTrip(id);

        return mm.map(foundTrip, TripRest.class);
    }

    // GET ALL DAYS FOR ONE TRIP
    @GetMapping("/trips/{tripId}/days")
    public List<DayRest> getDaysOfTrip(@PathVariable Long tripId) {

        List<DayDto> dayDto = dayService.getAllDaysForTrip(tripId);

        Type listType = new TypeToken<List<DayRest>>() {}.getType();
        return mm.map(dayDto, listType);

    }

    // EDIT TRIP DETAILS
    @PutMapping("/trips/{tripId}")
    public TripRest editTripDetails(@RequestBody TripDetailRequestModel newTripDetails, @PathVariable Long tripId) {

        TripDto newTrip = tripService.editTripDetails(newTripDetails, tripId);

        return mm.map(newTrip, TripRest.class);

    }

    // DELETE ONE TRIP
    @DeleteMapping("/trips/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
    }

    // GET ONE DAY OF ONE TRIP
    @GetMapping("/trips/{tripId}/days/{dayId}")
    public DayRest getDay(@PathVariable Long tripId,
                          @PathVariable Long dayId) {

        TripDto foundTrip = tripService.getTrip(tripId);
        List<DayDto> daysOTrip = foundTrip.getListOfDays();

        if (daysOTrip == null) {
            return null;
        }

        DayDto dayDto = dayService.getDay(dayId);

        return mm.map(dayDto, DayRest.class);
    }
}
