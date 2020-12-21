package io.github.batetolast1.wedderforecast.model.results;

import io.github.batetolast1.wedderforecast.model.location.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass

@NoArgsConstructor
@Getter
@Setter
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @PrePersist
    public void prePersist() {
        this.createdOn = LocalDateTime.now();
        this.updatedOn = null;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedOn = LocalDateTime.now();
    }
}
