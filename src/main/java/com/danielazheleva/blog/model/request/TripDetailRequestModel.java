package com.danielazheleva.blog.model.request;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class TripDetailRequestModel {

    private String tripTitle;
    private Date tripStartDate;
    private Integer tripDuration;
    private Set<PostBody> postBody;

    private Date postCreationDate;
    private Date postEditDate;

}
