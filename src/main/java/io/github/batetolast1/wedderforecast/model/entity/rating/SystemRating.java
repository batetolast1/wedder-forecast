package io.github.batetolast1.wedderforecast.model.entity.rating;

import io.github.batetolast1.wedderforecast.model.entity.rating.enums.SystemRatingValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "system_ratings")

@NoArgsConstructor
@Getter
@Setter
public class SystemRating extends Rating {

    private Integer totalPoints = 0;

    @Enumerated(EnumType.STRING)
    private SystemRatingValue overall;

    @Enumerated(EnumType.STRING)
    private SystemRatingValue temp;

    @Enumerated(EnumType.STRING)
    private SystemRatingValue pres;

    @Enumerated(EnumType.STRING)
    private SystemRatingValue precip;

    @Enumerated(EnumType.STRING)
    private SystemRatingValue cld;

    @Enumerated(EnumType.STRING)
    private SystemRatingValue wind;

    @Enumerated(EnumType.STRING)
    private SystemRatingValue hum;

    public void addPoints(int points) {
        this.totalPoints += points;
    }
}
