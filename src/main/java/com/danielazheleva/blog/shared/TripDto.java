package com.danielazheleva.blog.shared;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class TripDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String tripTitle;
    private Date tripStartDate;
    private List<DayDto> listOfDays;
    private Date postCreationDate;
    private Date postEditDate;
    private Boolean addDayInfo;


    @Override
    public String toString() {
        return "TripDto{" +
                "id=" + id +
                ", tripTitle='" + tripTitle + '\'' +
                ", tripStartDate=" + tripStartDate +
                ", listOfDays=" + listOfDays +
                ", postCreationDate=" + postCreationDate +
                ", postEditDate=" + postEditDate +
                ", addDayInfo=" + addDayInfo +
                '}';
    }
}
