package com.danielazheleva.blog.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayRequestModel {

    private Integer dayNumber;

    private String country;
    private String city;

    private String postText;

}
