package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name="TRIP")
public class TripEntity {

    @Id
    @GeneratedValue
    private Long id;

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
}
