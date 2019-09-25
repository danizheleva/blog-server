package com.danielazheleva.blog.repository;

import entity.TripEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<TripEntity, Long> {

    List<TripEntity> findAll();
}
