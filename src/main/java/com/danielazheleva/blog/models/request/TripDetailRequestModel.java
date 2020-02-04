package com.danielazheleva.blog.models.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TripDetailRequestModel {

    private String tripTitle;
    private Date tripStartDate;
    private List<DayRequestModel> listOfDays;
    private String tripSummary;

    private Date postCreationDate;
    private Date postEditDate;
}
