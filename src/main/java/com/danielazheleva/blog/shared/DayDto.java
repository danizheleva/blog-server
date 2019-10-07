package com.danielazheleva.blog.shared;

import lombok.Data;

@Data
public class DayDto {

    private Integer dayNumber;

    private String country;
    private String city;

    private String postText;

    private TripDto tripDetail;

}
