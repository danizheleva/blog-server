package com.danielazheleva.blog.models.request;

import lombok.Data;

@Data
public class DayRequestModel {

    private Integer dayNumber;

    private String country;
    private String city;

    private String postText;

}
