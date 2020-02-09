package com.danielazheleva.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Data
@Entity(name="days")
public class DayEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String dayTitle;

    @Column
    private Integer dayNumber;

    @Column
    private String postText;

    @ManyToOne
    @JoinColumn(name = "trips_id")
    private TripEntity tripDetail;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "DAY_TO_LOCATION",
            joinColumns = @JoinColumn(name = "days_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    Set<LocationEntity> locations;

    @Override
    public String toString() {
        return "DayEntity{" +
                "id=" + id +
                ", dayNumber=" + dayNumber +
                ", postText='" + postText + '\'' +
                ", tripDetail=" + tripDetail +
                '}';
    }
}
