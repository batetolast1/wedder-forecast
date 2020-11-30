package io.github.batetolast1.wedderforecast.dto;

import io.github.batetolast1.wedderforecast.model.entity.rating.enums.SystemRatingValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SystemRatingDto {

    private SystemRatingValue overall;
    private SystemRatingValue temp;
    private SystemRatingValue pres;
    private SystemRatingValue precip;
    private SystemRatingValue cld;
    private SystemRatingValue wind;
    private SystemRatingValue hum;
}
