package com.danielazheleva.blog.models.responce;

import lombok.Data;

@Data
public class DayRest {

    private Integer dayNumber;
    private String dayTitle;
    private String dayText;
    private String city;
    private String country;

}
