package com.danielazheleva.blog.services;

import com.danielazheleva.blog.shared.DayDto;

import java.util.List;

public interface DayService {

    List<DayDto> getAllDaysForTrip(Long tripId);

    DayDto getDay(Long dayId);
}
