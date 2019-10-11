package com.danielazheleva.blog.services.Impl;

import com.danielazheleva.blog.entity.DayEntity;
import com.danielazheleva.blog.repository.DayRepository;
import com.danielazheleva.blog.services.DayService;
import com.danielazheleva.blog.services.TripService;
import com.danielazheleva.blog.shared.DayDto;
import com.danielazheleva.blog.shared.TripDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DayServiceImpl implements DayService {

    @Autowired
    private TripService tripService;

    @Autowired
    private DayRepository dayRepository;

    private final ModelMapper mm = new ModelMapper();

    @Override
    public List<DayDto> getAllDaysForTrip(Long tripId) {

        List<DayDto> foundValue = new ArrayList<>();
        TripDto trip = tripService.getTrip(tripId);

        if ( trip == null ) { return foundValue; }

        return trip.getListOfDays();

    }

    @Override
    public DayDto getDay(Long dayId) {

        DayEntity dayEntity = dayRepository.getOne(dayId);

        return mm.map(dayEntity, DayDto.class);
    }
}
