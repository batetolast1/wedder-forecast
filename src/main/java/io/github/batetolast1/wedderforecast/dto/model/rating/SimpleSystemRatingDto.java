package io.github.batetolast1.wedderforecast.dto.model.rating;

import io.github.batetolast1.wedderforecast.model.rating.enums.OverallSystemRatingValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SimpleSystemRatingDto {

    private OverallSystemRatingValue overall;
}
