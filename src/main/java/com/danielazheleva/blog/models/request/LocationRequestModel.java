package com.danielazheleva.blog.models.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationRequestModel {

    private String country;
    private String city;

}
