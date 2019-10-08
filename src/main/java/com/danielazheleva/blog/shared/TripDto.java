package com.danielazheleva.blog.shared;

import com.danielazheleva.blog.models.request.DayRequestModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TripDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String tripTitle;
    private Date tripStartDate;
    private Integer tripDuration;
    private List<DayDto> listOfDays;
    private Date postCreationDate;
    private Date postEditDate;
    private Boolean addDayInfo;

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

    public List<DayDto> getListOfDays() {
        return listOfDays;
    }

    public void setListOfDays(List<DayDto> listOfDays) {
        this.listOfDays = listOfDays;
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

    public Boolean getAddDayInfo() {
        return addDayInfo;
    }

    public void setAddDayInfo(Boolean addDayInfo) {
        this.addDayInfo = addDayInfo;
    }
}
