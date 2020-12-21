package io.github.batetolast1.wedderforecast.model.weather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "PredictedDailyWeather")

@NoArgsConstructor
@Getter
@Setter
public class PredictedDailyWeather extends DailyWeather {

    @Column(name = "temp_avg_deviation")
    private Double tempAvgDeviation;

    @Column(name = "feels_like_avg_deviation")
    private Double feelsLikeAvgDeviation;

    @Column(name = "msl_pres_avg_deviation")
    private Double mslPresAvgDeviation;

    @Column(name = "precip_deviation")
    private Double precipDeviation;

    @Column(name = "snowfall_deviation")
    private Double snowfallDeviation;

    @Column(name = "cld_cvr_avg_deviation")
    private Double cldCvrAvgDeviation;

    @Column(name = "wind_spd_avg_deviation")
    private Double windSpdAvgDeviation;

    @Column(name = "rel_hum_avg_deviation")
    private Double relHumAvgDeviation;
}
