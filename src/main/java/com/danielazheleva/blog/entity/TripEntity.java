package com.danielazheleva.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(name="trips")
public class TripEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String tripTitle;

    @Column(nullable = false)
    private Date tripStartDate;

    @Column(nullable = false)
    private Integer tripDuration;

    @Column(nullable = false)
    private Date postCreationDate;

    @Column
    private Date postEditDate;

    @OneToMany(mappedBy = "tripDetails", cascade = CascadeType.ALL)
    private List<DayEntity> days;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    public Date getTripStartDate() {
        return tripStartDate;
    }

    public void setTripStartDate(Date tripStartDate) {
        this.tripStartDate = tripStartDate;
    }

    public Integer getTripDuration() {
        return tripDuration;
    }

    public void setTripDuration(Integer tripDuration) {
        this.tripDuration = tripDuration;
    }

    public Date getPostCreationDate() {
        return postCreationDate;
    }

    public void setPostCreationDate(Date postCreationDate) {
        this.postCreationDate = postCreationDate;
    }

    public Date getPostEditDate() {
        return postEditDate;
    }

    public void setPostEditDate(Date postEditDate) {
        this.postEditDate = postEditDate;
    }

    public List<DayEntity> getDays() {
        return days;
    }

    public void setDays(List<DayEntity> days) {
        this.days = days;
    }
}
