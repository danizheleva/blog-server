package com.danielazheleva.blog.repository;

import com.danielazheleva.blog.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface TripRepository extends JpaRepository<TripEntity, Long> {

    List<TripEntity> findAll();

}
