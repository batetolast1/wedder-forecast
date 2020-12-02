package io.github.batetolast1.wedderforecast.dto;

import io.github.batetolast1.wedderforecast.model.entity.rating.enums.SystemRatingValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SystemRatingDto {

    private SystemRatingValue temp;
    private SystemRatingValue feelsLike;
    private SystemRatingValue heatIndex;
    private SystemRatingValue mslPres;
    private SystemRatingValue precip;
    private SystemRatingValue snowfall;
    private SystemRatingValue cldCvr;
    private SystemRatingValue windSpd;
    private SystemRatingValue relHum;
    private SystemRatingValue overall;
}
