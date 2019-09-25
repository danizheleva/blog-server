package entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="postBody")
public class PostBodyEntity {

    @Id
    @GeneratedValue
    private Long post_id;

    @Column
    private Integer dayNumber;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String postText;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private TripEntity trip;

}
