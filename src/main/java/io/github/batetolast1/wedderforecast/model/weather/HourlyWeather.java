package io.github.batetolast1.wedderforecast.model.weather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "HourlyWeather")

@NoArgsConstructor
@Getter
@Setter
public class HourlyWeather extends Weather {

    /**
     * The temperature (degrees Fahrenheit)
     * sample data: "temp": 22.6
     */
    @Column(name = "temp")
    private Double temp;

    /**
     * The “feels like” temperature (degrees Fahrenheit)
     * sample data: "feelsLike": 14.4
     */
    @Column(name = "feels_like")
    private Double feelsLike;

    /**
     * The Mean Sea Level Pressure (mb (millibars))
     * sample data: "mslPres": 1027.1
     */
    @Column(name = "msl_pres")
    private Double mslPres;

    /**
     * Total precipitation (inches)
     * sample data: "precip": 0
     */
    private Double precip;

    /**
     * The total snowfall (inches)
     * sample data: "snowfall": 0
     */
    private Double snowfall;

    /**
     * Cloud cover as a percent value (Percent (between 0 and 100))
     * sample data: "cldCvr": 23
     */
    @Column(name = "cld_cvr")
    private Double cldCvr;

    /**
     * The Wind Speed at 10m above the surface (miles per hour)
     * sample data: "windSpd": 6.8
     */
    @Column(name = "wind_spd")
    private Double windSpd;

    /**
     * The relative humidity (Percent (between 0 and 100))
     * sample data: "relHum": 53
     */
    @Column(name = "rel_hum")
    private Double relHum;
}
