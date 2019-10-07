package com.danielazheleva.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity(name="trips")
public class TripEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String tripTitle;

    @Column(nullable = false)
    private Date tripStartDate;

    @Column(nullable = false)
    private Integer tripDuration;

    @Column(nullable = false)
    private Date postCreationDate;

    @Column
    private Date postEditDate;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<DayEntity> days;
}
