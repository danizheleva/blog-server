package com.danielazheleva.blog.repository;

import com.danielazheleva.blog.entity.TripEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends CrudRepository<TripEntity, Long> {

    List<TripEntity> findAll();
}
