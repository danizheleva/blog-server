package com.danielazheleva.blog.models.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TripDetailRequestModel {

    private String tripTitle;
    private Date tripStartDate;
    private Integer tripDuration;
    private List<DayRequestModel> listOfDays;

    private Date postCreationDate;
    private Date postEditDate;

    private Boolean addDayInfo;
}