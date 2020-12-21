package io.github.batetolast1.wedderforecast.model.rating.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SystemRatingValue {

    MISSING_DATA(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int points;
}
