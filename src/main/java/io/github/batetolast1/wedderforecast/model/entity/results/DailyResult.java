package io.github.batetolast1.wedderforecast.model.entity.results;

import io.github.batetolast1.wedderforecast.model.entity.rating.UserRating;
import io.github.batetolast1.wedderforecast.model.entity.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedDailyWeather;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "daily_results")

@NoArgsConstructor
@Getter
@Setter
public class DailyResult extends UserResult {

    @OneToMany
    private Set<DailyWeather> dailyWeathers;

    @OneToOne
    private PredictedDailyWeather predictedDailyWeather;

    @OneToOne
    private UserRating dailyUserRating;
}
