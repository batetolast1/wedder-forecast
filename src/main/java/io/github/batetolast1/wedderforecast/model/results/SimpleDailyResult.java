package io.github.batetolast1.wedderforecast.model.results;

import io.github.batetolast1.wedderforecast.model.weather.PredictedDailyWeather;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "simple_daily_results")

@NoArgsConstructor
@Getter
@Setter
public class SimpleDailyResult extends Result {

    @ManyToOne
    @JoinColumn(name = "predicted_daily_weather_id")
    private PredictedDailyWeather predictedDailyWeather;
}
