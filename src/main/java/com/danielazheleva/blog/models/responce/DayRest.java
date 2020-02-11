package com.danielazheleva.blog.models.responce;

import lombok.Data;

import java.util.List;

@Data
public class DayRest {

    private Integer dayNumber;
    private String dayTitle;
    private String dayText;
    private List<LocationRest> locations;

}
