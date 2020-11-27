package io.github.batetolast1.wedderforecast.model.entity.weather;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.rating.SystemRating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@MappedSuperclass

@NoArgsConstructor
@Getter
@Setter
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Location location;

    @OneToOne
    private SystemRating systemRating;

    /**
     * Timestamp is assumed to be in local time for the area or point of observation.
     * Sample data: "timestamp": "2016-01-01T01:00:00+01:00"
     */
    private ZonedDateTime timestamp;
}
