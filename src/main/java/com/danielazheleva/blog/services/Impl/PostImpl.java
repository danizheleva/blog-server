package com.danielazheleva.blog.services.Impl;

import com.danielazheleva.blog.model.request.PostBody;
import com.danielazheleva.blog.model.request.TripDetailRequestModel;
import com.danielazheleva.blog.repository.PostRepository;
import com.danielazheleva.blog.services.PostsService;
import entity.PostBodyEntity;
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
    private PostRepository postRepo;

    public List<TripEntity> allPosts(){
        return postRepo.findAll();
    }

    public void savePost(TripDetailRequestModel postDetail) {
//        TripEntity post = postRequestToEntityConvertor(postDetail);
//
//        try {
//            postRepo.save(post);
//            LOG.info("Post {} saved to database", post.getTrip_id());
//        } catch(Error err) {
//            LOG.info("Post {} was not saved. Error is {}", post.getTrip_id(), err.getCause());
//        }
    }

//    private PostBodyEntity postBodyConvertyor(PostBody in) {
//        PostBodyEntity postBody = new PostBodyEntity();
//        postBody.setCity(in.getCity());
//        postBody.setCountry(in.getCountry());
//        postBody.setDayNumber(in.getDayNumber());
//        postBody.setPostText(in.getPostText());
//        return postBody;
//    }

//    private TripEntity postRequestToEntityConvertor(TripDetailRequestModel in) {
//
//        TripEntity trip = new TripEntity();
//        trip.setTripTitle(in.getTripTitle());
//        trip.setTripStartDate(in.getTripStartDate());
//        trip.setTripDuration(in.getTripDuration());
//        trip.setPostBody(in.getPostBody());
//        trip.setPostCreationDate(in.getPostCreationDate());
//        trip.setPostEditDate(in.getPostEditDate());
//
//        return trip;
//    }
}
