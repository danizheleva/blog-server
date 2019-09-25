package com.danielazheleva.blog.model.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TripDetailRequestModel {

    private String tripTitle;
    private Date tripStartDate;
    private Integer tripDuration;
    private List<PostBody> postBody;

    private Date postCreationDate;
    private Date postEditDate;

}
