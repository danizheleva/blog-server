package com.danielazheleva.blog.services.Impl;

import com.danielazheleva.blog.model.request.Day;
import com.danielazheleva.blog.model.request.TripDetailRequestModel;
import com.danielazheleva.blog.repository.DayRepository;
import com.danielazheleva.blog.repository.TripRepository;
import com.danielazheleva.blog.services.PostsService;
import entity.DayEntity;
import entity.TripEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostImpl implements PostsService {

    public static Logger LOG = LoggerFactory.getLogger(PostImpl.class);

    @Autowired
    private TripRepository postRepo;

    @Autowired
    private DayRepository dayRepo;

    public List<TripEntity> allPosts(){
        return postRepo.findAll();
    }

    public void savePost(TripDetailRequestModel tripDetailRequest) {
        TripEntity tripEntity = postRequestToEntityConvertor(tripDetailRequest);

        try {
            postRepo.save(tripEntity);
            LOG.info("Post {} saved to database", tripEntity.getId());
            if(tripDetailRequest.getAddDayInfo()) {
                tripDetailRequest.getSetOfDays()
                        .forEach(x -> dayRepo.save(postBodyConvertor(x)));
            }
        } catch(Error err) {
            LOG.info("Post {} was not saved. Error is {}", tripEntity.getId(), err.getCause());
        }
    }

    private DayEntity postBodyConvertor(Day in) {
        DayEntity dayEntity = new DayEntity();
        dayEntity.setCity(in.getCity());
        dayEntity.setCountry(in.getCountry());
        dayEntity.setDayNumber(in.getDayNumber());
        dayEntity.setPostText(in.getPostText());
        return dayEntity;
    }

    private TripEntity postRequestToEntityConvertor(TripDetailRequestModel in) {

        TripEntity trip = new TripEntity();
        trip.setTripTitle(in.getTripTitle());
        trip.setTripStartDate(in.getTripStartDate());
        trip.setTripDuration(in.getTripDuration());
        trip.setPostCreationDate(in.getPostCreationDate());
        trip.setPostEditDate(in.getPostEditDate());

        return trip;
    }
}
