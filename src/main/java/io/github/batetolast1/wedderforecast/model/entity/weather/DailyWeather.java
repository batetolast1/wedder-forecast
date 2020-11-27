package io.github.batetolast1.wedderforecast.model.entity.weather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "daily_weathers")

@NoArgsConstructor
@Getter
@Setter
public class DailyWeather extends Weather {

    /**
     * The average temperature (degrees Fahrenheit)
     * sample data: "tempAvg": 22.6
     */
    @Column(name = "temp_avg")
    private Float tempAvg;

    /**
     * The average “feels like” temperature (degrees Fahrenheit)
     * sample data: "feelsLikeAvg": 14.4
     */
    @Column(name = "feels_like_avg")
    private Float feelsLikeAvg;

    /**
     * The average heat index (degrees Fahrenheit)
     * sample data: "heatIndexAvg": 22.6
     */
    @Column(name = "heat_index_avg")
    private Float heatIndexAvg;

    /**
     * The average Mean Sea Level Pressure (mb (millibars))
     * sample data: "mslPresAvg": 1027.1
     */
    @Column(name = "msl_pres_avg")
    private Float mslPresAvg;

    /**
     * Total precipitation (inches)
     * sample data: "precip": 0
     */
    private Float precip;

    /**
     * Total snowfall (inches)
     * sample data: "snowfall": 0
     */
    private Float snowfall;

    /**
     * Average cloud cover as a percent value (Percent (between 0 and 100))
     * sample data: "cldCvrAvg": 23
     */
    @Column(name = "cld_cvr_avg")
    private Float cldCvrAvg;

    /**
     * The average Wind Speed at 10m above the surface (miles per hour)
     * sample data: "windSpdAvg": 6.8
     */
    @Column(name = "wind_spd_avg")
    private Float windSpdAvg;

    /**
     * The average relative humidity (Percent (between 0 and 100))
     * sample data: "relHumAvg": 53
     */
    @Column(name = "rel_hum_avg")
    private Float relHumAvg;
}
