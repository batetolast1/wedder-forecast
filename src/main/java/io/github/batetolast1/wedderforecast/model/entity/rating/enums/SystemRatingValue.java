package io.github.batetolast1.wedderforecast.model.entity.rating.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SystemRatingValue {

    MISSING_DATA(0),
    VERY_UNSATISFIED(1),
    UNSATISFIED(2),
    NEUTRAL(3),
    SATISFIED(4),
    VERY_SATISFIED(5);

    private final int points;
}
