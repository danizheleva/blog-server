package com.danielazheleva.blog.services.Impl;

import com.danielazheleva.blog.shared.DayDto;
import com.danielazheleva.blog.shared.TripDto;
import com.danielazheleva.blog.repository.DayRepository;
import com.danielazheleva.blog.repository.TripRepository;
import com.danielazheleva.blog.services.PostsService;
import com.danielazheleva.blog.entity.TripEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostImpl implements PostsService {

    public static Logger LOG = LoggerFactory.getLogger(PostImpl.class);

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private DayRepository dayRepo;

    public List<TripDto> getAllPosts(){

        List<TripEntity> tripEntityList = tripRepository.findAll();

        ModelMapper mm = new ModelMapper();
        return mm.map(tripEntityList,  new TypeToken<List<TripDto>>(){}.getType());
    }

    public TripDto savePost(TripDto tripDto) {

        for(int i=0; i<tripDto.getListOfDays().size(); i++){
            DayDto day = tripDto.getListOfDays().get(i);
            day.setTripDetail(tripDto);
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TripEntity tripEntity = modelMapper.map(tripDto, TripEntity.class);

        TripEntity storedTripEntity = tripRepository.save(tripEntity);

        TripDto returnValue = modelMapper.map(storedTripEntity, TripDto.class);

        return returnValue;
    }
}
