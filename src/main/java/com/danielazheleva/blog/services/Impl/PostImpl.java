package com.danielazheleva.blog.services.Impl;

import com.danielazheleva.blog.model.request.TripDetailRequestModel;
import com.danielazheleva.blog.repository.PostRepository;
import com.danielazheleva.blog.services.PostsService;
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
        try {
            TripEntity post = postRequestToEntityConvertor(postDetail);
            postRepo.save(post);
            LOG.info("Post {} saved to database", post.getId());
        } catch(Error err) {
            LOG.info("Post {} was not saved. Error is {}", post.getId(), err.getCause());
        }
    }

    private TripEntity postRequestToEntityConvertor(TripDetailRequestModel in) {
        return new TripEntity(
                in.g
        )
    }
}
