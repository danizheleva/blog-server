package com.danielazheleva.blog.services.Impl;

import com.danielazheleva.blog.models.request.TripDetailRequestModel;
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
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private DayRepository dayRepo;

    public List<TripDto> getAllTrips(){

        List<TripEntity> tripEntityList = tripRepository.findAll();

        ModelMapper mm = new ModelMapper();
        return mm.map(tripEntityList,  new TypeToken<List<TripDto>>(){}.getType());
    }

    public TripDto saveTrip(TripDetailRequestModel tripDetailRequestModel) {

        TripDto tripDto = modelMapper.map(tripDetailRequestModel, TripDto.class);

        for(int i=0; i<tripDto.getListOfDays().size(); i++){
            DayDto day = tripDto.getListOfDays().get(i);
            day.setTripDetail(tripDto);
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TripEntity tripEntity = modelMapper.map(tripDto, TripEntity.class);

        TripEntity storedTripEntity = tripRepository.save(tripEntity);

        return modelMapper.map(storedTripEntity, TripDto.class);
    }

    public TripDto getTrip(Long tripId) {

        TripEntity tripEntity = tripRepository.getOne(tripId);

        return modelMapper.map(tripEntity, TripDto.class);
    }

    @Override
    public TripDto editTripDetails(TripDetailRequestModel newTripDetails, Long tripId) {

        TripEntity tripToEdit = tripRepository.getOne(tripId);

        // If no trip found, create new one
        if ( tripToEdit == null ) {
            TripDto newTripDto = modelMapper.map(newTripDetails, TripDto.class);
            TripEntity newTrip = modelMapper.map(newTripDto, TripEntity.class);
            TripEntity savedTrip = tripRepository.save(newTrip);
            return modelMapper.map(savedTrip, TripDto.class);
        }

        tripToEdit.setTripTitle(newTripDetails.getTripTitle());
        tripToEdit.setTripDuration(newTripDetails.getTripDuration());
        tripToEdit.setTripStartDate(newTripDetails.getTripStartDate());
        tripToEdit.setPostEditDate(newTripDetails.getPostEditDate());
        TripEntity savedTrip = tripRepository.save(tripToEdit);
        return modelMapper.map(savedTrip, TripDto.class);
    }

    public void deleteTrip(Long id) {

        try {
            tripRepository.deleteById(id);
        } catch (Error e) {
            LOG.warn("Could not delete trip with id {}", id);
        }

    }
}
