package com.danielazheleva.blog.shared;

import com.danielazheleva.blog.models.request.DayRequestModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
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
}