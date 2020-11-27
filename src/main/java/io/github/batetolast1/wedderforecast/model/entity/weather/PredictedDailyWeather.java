package io.github.batetolast1.wedderforecast.model.entity.weather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "predicted_daily_weathers")

@NoArgsConstructor
@Getter
@Setter
public class PredictedDailyWeather extends DailyWeather {

    @Column(name = "temp_avg_deviation")
    private StandardDeviation tempAvgDeviation;

    @Column(name = "feels_like_avg_deviation")
    private StandardDeviation feelsLikeAvgDeviation;

    @Column(name = "heat_index_avg_deviation")
    private StandardDeviation heatIndexAvgDeviation;

    @Column(name = "msl_pres_avg_deviation")
    private StandardDeviation mslPresAvgDeviation;

    @Column(name = "precip_deviation")
    private StandardDeviation precipDeviation;

    @Column(name = "snowfall_deviation")
    private StandardDeviation snowfallDeviation;

    @Column(name = "cld_cvr_avg_deviation")
    private StandardDeviation cldCvrAvgDeviation;

    @Column(name = "wind_spd_avg_deviation")
    private StandardDeviation windSpdAvgDeviation;

    @Column(name = "rel_hum_avg_deviation")
    private StandardDeviation relHumAvgDeviation;
}
