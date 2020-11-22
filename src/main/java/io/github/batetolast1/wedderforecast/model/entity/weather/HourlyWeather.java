package io.github.batetolast1.wedderforecast.model.entity.weather;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.rating.SystemRating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hourly_weathers")

@NoArgsConstructor
@Getter
@Setter
public class HourlyWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Location location;

    @OneToOne
    SystemRating systemRating;

    // DATE & TIME

    // timestamp is assumed to be in local time for the area or point of observation.
    // sample data:  "timestamp": "2016-01-01T01:00:00+01:00"
    @Column(name = "date_time")
    private LocalDateTime localDateTime;

    // TEMPERATURE

    // The temperature (degrees Fahrenheit)
    // sample data: "temp": 22.6,
    @Column(name = "temp")
    private Float temp;

    // The “feels like” temperature (degrees Fahrenheit)
    // sample data: "feelsLike": 14.4,
    @Column(name = "feels_like")
    private Float feelsLike;

    // The heat index (degrees Fahrenheit)
    // sample data: "heatIndex": 22.6,
    @Column(name = "heat_index")
    private Float heatIndex;

    // PRESSURE

    // The Mean Sea Level Pressure (mb (millibars))
    // sample data: "mslPres": 1027.1,
    @Column(name = "msl_pres")
    private Float mslPres;

    // PRECIPITATION

    // Total precipitation (inches)
    // sample data: "precip": 0,
    @Column(name = "precip")
    private Float precip;

    // The total snowfall (inches)
    // sample data: "snowfall": 0,
    private Float snowfall;

    // CLOUDS & WIND

    // Cloud cover as a percent value (Percent (between 0 and 100))
    // sample data: "cldCvr": 23,
    @Column(name = "cld_cvr")
    private Float cldCvr;

    // The Wind Speed at 10m above the surface (miles per hour)
    // sample data: "windSpd": 6.8,
    @Column(name = "wind_spd")
    private Float windSpd;

    // HUMIDITY

    // The relative humidity (Percent (between 0 and 100))
    // sample data: "relHum": 53,
    @Column(name = "rel_hum")
    private Float relHum;
}
