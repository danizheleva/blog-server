package com.danielazheleva.blog.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayRequestModel {

    private Integer dayNumber;
    private String dayTitle;

    private List<LocationRequestModel> locations;

    private String postText;

}
