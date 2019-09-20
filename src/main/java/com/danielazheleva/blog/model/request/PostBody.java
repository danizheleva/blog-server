package com.danielazheleva.blog.model.request;

import lombok.Data;

@Data
public class PostBody {

    private Integer dayNumber;
    private String country;
    private String city;
    private String description;

}
