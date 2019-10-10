package com.danielazheleva.blog.models.responce;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TripRest {

    private String tripName;
    private Date tripStartTime;
    private Integer tripLength;
    private List<DayRest> listOfDays;
    private Date tripCreationDate;
    private Date tripEditDate;

}
