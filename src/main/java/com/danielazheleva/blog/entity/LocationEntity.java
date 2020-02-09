package com.danielazheleva.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "location")
public class LocationEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "CITY", nullable = false)
    private String city;

    @ManyToMany(mappedBy = "locations", cascade = CascadeType.ALL)
    Set<DayEntity> daysInLocation;
}
