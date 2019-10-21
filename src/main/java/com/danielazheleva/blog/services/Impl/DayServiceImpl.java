package com.danielazheleva.blog.services.Impl;

import com.danielazheleva.blog.entity.DayEntity;
import com.danielazheleva.blog.entity.TripEntity;
import com.danielazheleva.blog.exceptions.DayServiceException;
import com.danielazheleva.blog.models.request.DayRequestModel;
import com.danielazheleva.blog.models.responce.ErrorMessages;
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

        if ( trip == null ) {
            throw new DayServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMsg());
        }

        return trip.getListOfDays();

    }

    @Override
    public DayDto getDay(Long dayId) {

        DayEntity dayEntity = dayRepository.getOne(dayId);

        if(dayEntity == null ){
            throw new DayServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMsg());
        }

        return mm.map(dayEntity, DayDto.class);
    }

    @Override
    public DayDto editDay(DayRequestModel newDay,
                          Long tripId,
                          Long dayId) {

        DayEntity dayToEditEntity = dayRepository.getOne(dayId);

        if (dayToEditEntity == null) {
            throw new DayServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMsg());
        }

        TripDto correspondingTrip = tripService.getTrip(tripId);

        int lengthOfTrip = correspondingTrip.getListOfDays().size();

        if( newDay.getDayNumber() > lengthOfTrip ) {
            throw new DayServiceException(ErrorMessages.DAY_OUT_OF_BOUND.getErrorMsg());
        }

        dayToEditEntity.setDayNumber(newDay.getDayNumber());
        dayToEditEntity.setCity(newDay.getCity());
        dayToEditEntity.setCountry(newDay.getCountry());
        dayToEditEntity.setPostText(newDay.getPostText());

        dayRepository.save(dayToEditEntity);
        return mm.map(dayToEditEntity, DayDto.class);
    }

    @Override
    public TripDto createDay(DayRequestModel day, Long tripId) {

        TripDto tripDto = tripService.getTrip(tripId);
        TripEntity tripEntity = mm.map(tripDto, TripEntity.class);

        DayDto dayDto = mm.map(day, DayDto.class);
        DayEntity dayEntity = mm.map(dayDto, DayEntity.class);
        tripEntity.getListOfDays().add(dayEntity);
        dayEntity.setTripDetail(tripEntity);

        dayRepository.save(dayEntity);

        return mm.map(tripEntity, TripDto.class);
    }

    @Override
    public void deleteDay(Long dayId) {

        if(dayRepository.getOne(dayId) == null) {
            throw new DayServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMsg());
        }

        dayRepository.deleteById(dayId);
    }
}
