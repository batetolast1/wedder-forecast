package io.github.batetolast1.wedderforecast.model.results;

import io.github.batetolast1.wedderforecast.model.rating.UserRating;
import io.github.batetolast1.wedderforecast.model.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.weather.PredictedDailyWeather;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "daily_results")

@NoArgsConstructor
@Getter
@Setter
public class DailyResult extends UserResult {

    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<DailyWeather> dailyWeathers;

    @OneToOne
    private PredictedDailyWeather predictedDailyWeather;

    @OneToOne
    private UserRating dailyUserRating;
}
