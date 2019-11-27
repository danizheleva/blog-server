package com.danielazheleva.blog.shared;

import lombok.Data;

import java.io.Serializable;

@Data
public class DayDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String dayTitle;
    private Integer dayNumber;
    private String country;
    private String city;
    private String postText;
    private TripDto tripDetail;

}
