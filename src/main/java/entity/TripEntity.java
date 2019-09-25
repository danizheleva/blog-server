package entity;

import com.danielazheleva.blog.model.request.PostBody;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name="trip")
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

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    @JoinColumn
    private PostBody postBody;

    @Column(nullable = false)
    private Date postCreationDate;

    @Column
    private Date postEditDate;
}
