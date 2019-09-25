package com.danielazheleva.blog.model.request;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class TripDetailRequestModel {

    private String tripTitle;
    private Date tripStartDate;
    private Integer tripDuration;
    private Set<Day> day;

    private Date postCreationDate;
    private Date postEditDate;

    private Boolean addDayInfo;
    private Set<Day> setOfDays;
}
