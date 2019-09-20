package com.danielazheleva.blog.model.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostDetailRequestModel {

    private String tripTitle;
    private Date tripStartDate;
    private List<PostBody> postBody;

    private String postCreationDate;

}
