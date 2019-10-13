package com.danielazheleva.blog.services;

import com.danielazheleva.blog.models.request.DayRequestModel;
import com.danielazheleva.blog.shared.DayDto;
import com.danielazheleva.blog.shared.TripDto;

import java.util.List;

public interface DayService {

    List<DayDto> getAllDaysForTrip(Long tripId);

    DayDto getDay(Long dayId);

    void deleteDay(Long dayId);

    DayDto editDay(DayRequestModel newDay, Long tripId, Long dayId);

    TripDto createDay(DayRequestModel day, Long tripId);
}
