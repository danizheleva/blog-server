package com.danielazheleva.blog.entity;

import com.danielazheleva.blog.shared.TripDto;

import javax.persistence.*;
import java.io.Serializable;


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

    @ManyToOne
    @JoinColumn(name = "trips_id")
    private TripEntity tripDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public TripEntity getTripDetails() {
        return tripDetails;
    }

    public void setTripDetails(TripEntity tripDetails) {
        this.tripDetails = tripDetails;
    }
}
