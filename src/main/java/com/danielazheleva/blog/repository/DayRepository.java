package com.danielazheleva.blog.repository;

import com.danielazheleva.blog.entity.DayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface DayRepository extends JpaRepository<DayEntity, Long> {

}
