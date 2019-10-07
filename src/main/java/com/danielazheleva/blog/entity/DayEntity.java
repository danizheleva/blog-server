package com.danielazheleva.blog.entity;

import com.danielazheleva.blog.shared.TripDto;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name="days")
public class DayEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    @Column
    private Integer dayNumber;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String postText;

    @ManyToOne(targetEntity = TripEntity.class)
    @JoinColumn(name = "trips_id")
    private TripDto trip;

}
