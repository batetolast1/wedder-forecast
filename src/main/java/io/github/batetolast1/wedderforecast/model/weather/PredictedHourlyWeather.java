package io.github.batetolast1.wedderforecast.model.weather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("PredictedHourlyWeather")

@NoArgsConstructor
@Getter
@Setter
public class PredictedHourlyWeather extends HourlyWeather {

    @Column(name = "temp_deviation")
    private Double tempDeviation;

    @Column(name = "feels_like_deviation")
    private Double feelsLikeDeviation;

    @Column(name = "heat_index_deviation")
    private Double heatIndexDeviation;

    @Column(name = "msl_pres_deviation")
    private Double mslPresDeviation;

    @Column(name = "precip_deviation")
    private Double precipDeviation;

    @Column(name = "snowfall_deviation")
    private Double snowfallDeviation;

    @Column(name = "cld_avg_deviation")
    private Double cldCvrDeviation;

    @Column(name = "wind_avg_deviation")
    private Double windSpdDeviation;

    @Column(name = "rel_avg_deviation")
    private Double relHumDeviation;
}
