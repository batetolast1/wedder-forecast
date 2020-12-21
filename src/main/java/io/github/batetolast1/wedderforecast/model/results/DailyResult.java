package io.github.batetolast1.wedderforecast.model.results;

import io.github.batetolast1.wedderforecast.model.rating.enums.UserRatingValue;
import io.github.batetolast1.wedderforecast.model.user.User;
import io.github.batetolast1.wedderforecast.model.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.weather.PredictedDailyWeather;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "daily_results")

@NoArgsConstructor
@Getter
@Setter
public class DailyResult extends Result {

    @ManyToOne
    @JoinColumn(name = "predicted_daily_weather_id")
    private PredictedDailyWeather predictedDailyWeather;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "daily_result_daily_weather",
            joinColumns = @JoinColumn(name = "daily_result_id"),
            inverseJoinColumns = @JoinColumn(name = "daily_weather_id"))
    private Set<DailyWeather> dailyWeathers = new HashSet<>();

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_rating_value")
    private UserRatingValue userRatingValue;
}
