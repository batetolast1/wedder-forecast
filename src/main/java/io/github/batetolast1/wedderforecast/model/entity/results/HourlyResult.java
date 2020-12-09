package io.github.batetolast1.wedderforecast.model.entity.results;

import io.github.batetolast1.wedderforecast.model.entity.rating.UserRating;
import io.github.batetolast1.wedderforecast.model.entity.weather.HourlyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedHourlyWeather;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "hourly_results")

@NoArgsConstructor
@Getter
@Setter
public class HourlyResult extends UserResult {

    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<HourlyWeather> hourlyWeathers;

    @OneToOne
    private PredictedHourlyWeather predictedHourlyWeather;

    @OneToOne
    private UserRating hourlyUserRating;
}
