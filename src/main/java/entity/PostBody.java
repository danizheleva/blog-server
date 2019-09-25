package entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="postBody")
public class PostBody {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Integer dayNumber;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String postText;

    @ManyToOne
    private TripEntity trip;

}
