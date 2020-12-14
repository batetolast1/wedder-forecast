package io.github.batetolast1.wedderforecast.model.weather;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.batetolast1.wedderforecast.converter.StringToLocalDateTimeConverter;
import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.rating.SystemRating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weathers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "weather_type")

@NoArgsConstructor
@Getter
@Setter
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Location location;

    @OneToOne
    private SystemRating systemRating;

    /**
     * Timestamp is assumed to be in local time for the area or point of observation.
     * Sample data: "timestamp": "2016-01-01T01:00:00+01:00"
     */
    @JsonDeserialize(converter = StringToLocalDateTimeConverter.class)
    private LocalDateTime timestamp;
}
