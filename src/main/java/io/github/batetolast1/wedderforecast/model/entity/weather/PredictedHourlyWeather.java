package io.github.batetolast1.wedderforecast.model.entity.weather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "predicted_hourly_weathers")

@NoArgsConstructor
@Getter
@Setter
public class PredictedHourlyWeather extends HourlyWeather {

    @Column(name = "temp_deviation")
    private StandardDeviation tempDeviation;

    @Column(name = "feels_like_deviation")
    private StandardDeviation feelsLikeDeviation;

    @Column(name = "heat_index_deviation")
    private StandardDeviation heatIndexDeviation;

    @Column(name = "msl_pres_deviation")
    private StandardDeviation mslPresDeviation;

    @Column(name = "precip_deviation")
    private StandardDeviation precipDeviation;

    @Column(name = "snowfall_deviation")
    private StandardDeviation snowfallDeviation;

    @Column(name = "cld_avg_deviation")
    private StandardDeviation cldCvrDeviation;

    @Column(name = "wind_avg_deviation")
    private StandardDeviation windSpdDeviation;

    @Column(name = "rel_avg_deviation")
    private StandardDeviation relHumDeviation;
}
