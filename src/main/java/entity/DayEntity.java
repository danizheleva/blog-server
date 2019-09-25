package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity(name="DAY")
public class DayEntity {

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TRIP_id", nullable = false)
   // @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TripEntity trip;

}
