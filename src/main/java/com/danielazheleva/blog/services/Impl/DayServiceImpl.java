package com.danielazheleva.blog.services.Impl;

import com.danielazheleva.blog.entity.DayEntity;
import com.danielazheleva.blog.entity.TripEntity;
import com.danielazheleva.blog.models.request.DayRequestModel;
import com.danielazheleva.blog.repository.DayRepository;
import com.danielazheleva.blog.services.DayService;
import com.danielazheleva.blog.services.TripService;
import com.danielazheleva.blog.shared.DayDto;
import com.danielazheleva.blog.shared.TripDto;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DayServiceImpl implements DayService {

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(TripServiceImpl.class);

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

    @Override
    public DayDto editDay(DayRequestModel newDay,
                          Long tripId,
                          Long dayId) {

        TripDto correspondingTrip = tripService.getTrip(tripId);
        DayEntity dayToEditEntity = dayRepository.getOne(dayId);
        TripEntity tripEntity = mm.map(correspondingTrip, TripEntity.class);

        int lengthOfTrip = correspondingTrip.getListOfDays().size();

        dayToEditEntity.setCity(newDay.getCity());
        dayToEditEntity.setCountry(newDay.getCountry());
        if( newDay.getDayNumber() > lengthOfTrip ) {
            LOG.warn("You are trying to add a new day");
        } else {
            dayToEditEntity.setDayNumber(newDay.getDayNumber());
        }
        dayToEditEntity.setPostText(newDay.getPostText());
        dayToEditEntity.setTripDetail(tripEntity);

        dayRepository.save(dayToEditEntity);
        return mm.map(dayToEditEntity, DayDto.class);
    }

    @Override
    public TripDto createDay(DayRequestModel day, Long tripId) {

        TripDto tripDto = tripService.getTrip(tripId);
        TripEntity tripEntity = mm.map(tripDto, TripEntity.class);

        DayDto dayDto = mm.map(day, DayDto.class);
        DayEntity dayEntity = mm.map(dayDto, DayEntity.class);
        dayEntity.setTripDetail(tripEntity);

        DayEntity savedDay = dayRepository.save(dayEntity);

        tripEntity.getListOfDays().add(savedDay);

        TripDto editedTrip = mm.map(tripEntity, TripDto.class);

        return editedTrip;
    }

    @Override
    public void deleteDay(Long dayId) {
        dayRepository.deleteById(dayId);
    }
}
