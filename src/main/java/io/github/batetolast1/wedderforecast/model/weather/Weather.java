package io.github.batetolast1.wedderforecast.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.batetolast1.wedderforecast.converter.StringToLocalDateTimeConverter;
import io.github.batetolast1.wedderforecast.model.location.PostalCoordinate;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postal_coordinate_id")
    private PostalCoordinate postalCoordinate;

    /**
     * Timestamp is assumed to be in local time for the area or point of observation.
     * Sample data: "timestamp": "2016-01-01T01:00:00+01:00"
     */
    @JsonProperty("timestamp")
    @JsonDeserialize(converter = StringToLocalDateTimeConverter.class)
    private LocalDateTime localDateTime;

    @OneToOne
    private SystemRating systemRating;
}
