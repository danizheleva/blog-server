package com.danielazheleva.blog.services.Impl;

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

    public static Logger LOG = LoggerFactory.getLogger(TripServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private DayRepository dayRepo;

    public List<TripDto> getAllTrips(){

        List<TripEntity> tripEntityList = tripRepository.findAll();

        ModelMapper mm = new ModelMapper();
        return mm.map(tripEntityList,  new TypeToken<List<TripDto>>(){}.getType());
    }

    public TripDto saveTrip(TripDto tripDto) {

        for(int i=0; i<tripDto.getListOfDays().size(); i++){
            DayDto day = tripDto.getListOfDays().get(i);
            day.setTripDetail(tripDto);
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TripEntity tripEntity = modelMapper.map(tripDto, TripEntity.class);

        TripEntity storedTripEntity = tripRepository.save(tripEntity);

        TripDto returnValue = modelMapper.map(storedTripEntity, TripDto.class);

        return returnValue;
    }

    public TripDto getTrip(Long tripId) {

        TripEntity tripEntity = tripRepository.findTripById(tripId);

        return modelMapper.map(tripEntity, TripDto.class);
    }

    public void deleteTrip(Long id) {

        try {
            tripRepository.deleteById(id);
        } catch (Error e) {
            LOG.warn("Could not delete trip with id {}", id);
        }

    }
}
