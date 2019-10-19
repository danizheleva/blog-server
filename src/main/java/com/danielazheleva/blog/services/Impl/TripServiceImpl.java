package com.danielazheleva.blog.services.Impl;

import com.danielazheleva.blog.exceptions.TripServiceException;
import com.danielazheleva.blog.models.request.TripDetailRequestModel;
import com.danielazheleva.blog.models.responce.ErrorMessages;
import com.danielazheleva.blog.shared.DayDto;
import com.danielazheleva.blog.shared.TripDto;
import com.danielazheleva.blog.repository.DayRepository;
import com.danielazheleva.blog.repository.TripRepository;
import com.danielazheleva.blog.services.TripService;
import com.danielazheleva.blog.entity.TripEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    private final static Logger LOG = LoggerFactory.getLogger(TripServiceImpl.class);
    private final ModelMapper mm = new ModelMapper();

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private DayRepository dayRepo;

    public List<TripDto> getAllTrips(){

        List<TripEntity> tripEntityList = tripRepository.findAll();

        if( tripEntityList == null ) {
            throw new TripServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMsg());
        }

        return mm.map(tripEntityList,  new TypeToken<List<TripDto>>(){}.getType());
    }

    public TripDto saveTrip(TripDetailRequestModel tripDetailRequestModel) {

        TripDto tripDto = mm.map(tripDetailRequestModel, TripDto.class);

        for(int i=0; i<tripDto.getListOfDays().size(); i++){
            DayDto day = tripDto.getListOfDays().get(i);
            day.setTripDetail(tripDto);
        }

        mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TripEntity tripEntity = mm.map(tripDto, TripEntity.class);

        TripEntity storedTripEntity = tripRepository.save(tripEntity);

        return mm.map(storedTripEntity, TripDto.class);
    }

    public TripDto getTrip(Long tripId) {

        TripEntity tripEntity = tripRepository.getOne(tripId);

        if( tripEntity == null ) {
            throw new TripServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMsg());
        }

        return mm.map(tripEntity, TripDto.class);
    }

    @Override
    public TripDto editTripDetails(TripDetailRequestModel newTripDetails, Long tripId) {

        TripEntity tripToEdit = tripRepository.getOne(tripId);

        // If no trip found, create new one
        if ( tripToEdit == null ) {
            TripDto newTripDto = mm.map(newTripDetails, TripDto.class);
            TripEntity newTrip = mm.map(newTripDto, TripEntity.class);
            TripEntity savedTrip = tripRepository.save(newTrip);
            return mm.map(savedTrip, TripDto.class);
        }

        tripToEdit.setTripTitle(newTripDetails.getTripTitle());
        tripToEdit.setTripStartDate(newTripDetails.getTripStartDate());
        tripToEdit.setPostEditDate(newTripDetails.getPostEditDate());
        TripEntity savedTrip = tripRepository.save(tripToEdit);
        return mm.map(savedTrip, TripDto.class);
    }

    public void deleteTrip(Long id) {

        if(tripRepository.getOne(id) == null) {
            throw new TripServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMsg());
        }

        tripRepository.deleteById(id);
    }
}
