package com.danielazheleva.blog.shared;

import lombok.Data;

import java.io.Serializable;

public class DayDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private Integer dayNumber;
    private String country;
    private String city;
    private String postText;
    private TripDto tripDetail;

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

    public TripDto getTripDetail() {
        return tripDetail;
    }

    public void setTripDetail(TripDto tripDetail) {
        this.tripDetail = tripDetail;
    }
}
