package com.danielazheleva.blog.repository;

import com.danielazheleva.blog.entity.DayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<DayEntity, Long> {

}
