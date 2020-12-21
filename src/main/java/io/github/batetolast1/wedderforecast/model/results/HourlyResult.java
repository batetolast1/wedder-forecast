package io.github.batetolast1.wedderforecast.model.results;

import io.github.batetolast1.wedderforecast.model.rating.enums.UserRatingValue;
import io.github.batetolast1.wedderforecast.model.user.User;
import io.github.batetolast1.wedderforecast.model.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.model.weather.PredictedHourlyWeather;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hourly_results")

@NoArgsConstructor
@Getter
@Setter
public class HourlyResult extends Result {

    @ManyToOne
    @JoinColumn(name = "predicted_hourly_weather_id")
    private PredictedHourlyWeather predictedHourlyWeather;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hourly_result_hourly_weather",
            joinColumns = @JoinColumn(name = "hourly_result_id"),
            inverseJoinColumns = @JoinColumn(name = "hourly_weather_id"))
    private Set<HourlyWeather> hourlyWeathers = new HashSet<>();

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_rating_value")
    private UserRatingValue userRatingValue;
}
