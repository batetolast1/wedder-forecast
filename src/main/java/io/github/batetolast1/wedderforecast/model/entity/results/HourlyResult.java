package io.github.batetolast1.wedderforecast.model.entity.results;

import io.github.batetolast1.wedderforecast.model.entity.rating.UserRating;
import io.github.batetolast1.wedderforecast.model.entity.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedHourlyWeather;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "hourly_results")

@NoArgsConstructor
@Getter
@Setter
public class HourlyResult extends UserResult {

    @OneToMany
    private Set<HourlyWeather> hourlyWeathers;

    @OneToOne
    private PredictedHourlyWeather predictedHourlyWeather;

    @OneToOne
    private UserRating hourlyUserRating;
}
