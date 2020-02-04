package com.danielazheleva.blog.models.responce;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TripRest {

    private String tripTitle;
    private Date tripStartDate;
    private String tripSummary;
    private List<DayRest> listOfDays;
    private Date postCreationDate;
    private Date postEditDate;

}
