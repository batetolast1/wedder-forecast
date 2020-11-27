package io.github.batetolast1.wedderforecast.model.entity.weather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hourly_weathers")

@NoArgsConstructor
@Getter
@Setter
public class HourlyWeather extends Weather {

    /**
     * The temperature (degrees Fahrenheit)
     * sample data: "temp": 22.6
     */
    @Column(name = "temp")
    private Float temp;

    /**
     * The “feels like” temperature (degrees Fahrenheit)
     * sample data: "feelsLike": 14.4
     */
    @Column(name = "feels_like")
    private Float feelsLike;

    /**
     * The heat index (degrees Fahrenheit)
     * sample data: "heatIndex": 22.6
     */
    @Column(name = "heat_index")
    private Float heatIndex;

    /**
     * The Mean Sea Level Pressure (mb (millibars))
     * sample data: "mslPres": 1027.1
     */
    @Column(name = "msl_pres")
    private Float mslPres;

    /**
     * Total precipitation (inches)
     * sample data: "precip": 0
     */
    private Float precip;

    /**
     * The total snowfall (inches)
     * sample data: "snowfall": 0
     */
    private Float snowfall;

    /**
     * Cloud cover as a percent value (Percent (between 0 and 100))
     * sample data: "cldCvr": 23
     */
    @Column(name = "cld_cvr")
    private Float cldCvr;

    /**
     * The Wind Speed at 10m above the surface (miles per hour)
     * sample data: "windSpd": 6.8
     */
    @Column(name = "wind_spd")
    private Float windSpd;

    /**
     * The relative humidity (Percent (between 0 and 100))
     * sample data: "relHum": 53
     */
    @Column(name = "rel_hum")
    private Float relHum;
}
