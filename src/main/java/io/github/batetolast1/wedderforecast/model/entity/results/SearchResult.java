package io.github.batetolast1.wedderforecast.model.entity.results;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.rating.UserRating;
import io.github.batetolast1.wedderforecast.model.entity.user.User;
import io.github.batetolast1.wedderforecast.model.entity.weather.DailyWeather;
import io.github.batetolast1.wedderforecast.model.entity.weather.HourlyWeather;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "search_results")

@NoArgsConstructor
@Getter
@Setter
public class SearchResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    private LocalDateTime localDateTime;

    @ManyToOne
    private User user;

    @OneToOne
    private Location location;

    @OneToOne
    private DailyWeather dailyWeather;

    @OneToOne
    private UserRating dailyRating;

    @OneToOne
    private HourlyWeather hourlyWeather;

    @OneToOne
    private UserRating hourlyRating;

    @PrePersist
    public void prePersist() {
        this.createdOn = LocalDateTime.now();
        this.updatedOn = null;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedOn = LocalDateTime.now();
    }
}
