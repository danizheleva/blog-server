package com.danielazheleva.blog.services.Impl;

import com.danielazheleva.blog.shared.DayDto;
import com.danielazheleva.blog.shared.TripDto;
import com.danielazheleva.blog.repository.DayRepository;
import com.danielazheleva.blog.repository.TripRepository;
import com.danielazheleva.blog.services.PostsService;
import com.danielazheleva.blog.entity.TripEntity;
import org.modelmapper.ModelMapper;
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

    public List<TripEntity> getAllPosts(){
        return tripRepository.findAll();
    }

    public TripDto savePost(TripDto tripDto) {


        for(int i=0; i<tripDto.getListOfDays().size(); i++){
            DayDto day = tripDto.getListOfDays().get(i);
            day.setTripDetail(tripDto);
            tripDto.getListOfDays().set(i, day);
        }

        ModelMapper modelMapper = new ModelMapper();
        TripEntity tripEntity = modelMapper.map(tripDto, TripEntity.class);

        TripEntity storedTripEntity = tripRepository.save(tripEntity);

        TripDto returnValue = new TripDto();
        BeanUtils.copyProperties(storedTripEntity, returnValue);

        return returnValue;
    }
}
