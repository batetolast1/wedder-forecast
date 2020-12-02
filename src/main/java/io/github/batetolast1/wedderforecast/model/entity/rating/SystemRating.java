package io.github.batetolast1.wedderforecast.model.entity.rating;

import io.github.batetolast1.wedderforecast.model.entity.rating.enums.OverallSystemRatingValue;
import io.github.batetolast1.wedderforecast.model.entity.rating.enums.SystemRatingValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "system_ratings")

@NoArgsConstructor
@Getter
@Setter
public class SystemRating extends Rating {

    private Integer totalPoints = 0;

    @Enumerated(EnumType.STRING)
    private SystemRatingValue temp;

    @Enumerated(EnumType.STRING)
    @Column(name = "feels_like")
    private SystemRatingValue feelsLike;

    @Enumerated(EnumType.STRING)
    @Column(name = "heat_index")
    private SystemRatingValue heatIndex;

    @Enumerated(EnumType.STRING)
    @Column(name = "msl_pres")
    private SystemRatingValue mslPres;

    @Enumerated(EnumType.STRING)
    private SystemRatingValue precip;

    @Enumerated(EnumType.STRING)
    private SystemRatingValue snowfall;

    @Enumerated(EnumType.STRING)
    private SystemRatingValue cldCvr;

    @Enumerated(EnumType.STRING)
    private SystemRatingValue windSpd;

    @Enumerated(EnumType.STRING)
    private SystemRatingValue relHum;

    @Enumerated(EnumType.STRING)
    private OverallSystemRatingValue overall;

    public void addPoints(int points) {
        this.totalPoints += points;
    }
}
