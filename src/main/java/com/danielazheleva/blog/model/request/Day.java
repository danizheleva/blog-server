package com.danielazheleva.blog.model.request;

import lombok.Data;

@Data
public class Day {

    private Integer dayNumber;

    private String country;
    private String city;

    private String postText;

}
