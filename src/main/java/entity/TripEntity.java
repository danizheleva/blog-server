package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name="trip")
public class TripEntity {

    @Id
    @GeneratedValue
    private Long trip_id;

    @Column(nullable = false)
    private String tripTitle;

    @Column(nullable = false)
    private Date tripStartDate;

    @Column(nullable = false)
    private Integer tripDuration;

    @OneToMany(mappedBy = "trip")
    private Set<PostBodyEntity> postBody;

    @Column(nullable = false)
    private Date postCreationDate;

    @Column
    private Date postEditDate;
}
