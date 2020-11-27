package io.github.batetolast1.wedderforecast.model.entity.results;

import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedDailyWeather;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "simple_results")

@NoArgsConstructor
@Getter
@Setter
public class SimpleResult extends Result {

    @OneToOne
    private PredictedDailyWeather predictedDailyWeather;
}
