package com.danielazheleva.blog.entity;

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
    private String dayTitle;

    @Column
    private Integer dayNumber;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String postText;

    @ManyToOne
    @JoinColumn(name = "trips_id")
    private TripEntity tripDetail;

    @Override
    public String toString() {
        return "DayEntity{" +
                "id=" + id +
                ", dayNumber=" + dayNumber +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postText='" + postText + '\'' +
                ", tripDetail=" + tripDetail +
                '}';
    }
}
