package com.danielazheleva.blog.services;

import com.danielazheleva.blog.models.request.TripDetailRequestModel;
import com.danielazheleva.blog.shared.TripDto;

import java.util.List;

public interface TripService {

    List<TripDto> getAllTrips();

    TripDto saveTrip(TripDetailRequestModel tripDetailRequestModel);

    TripDto getTrip(Long tripId);

    void deleteTrip(Long tripId);

    TripDto editTripDetails(TripDetailRequestModel newTripDetails, Long tripId);

}
