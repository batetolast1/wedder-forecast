package io.github.batetolast1.wedderforecast.dto.model.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostalCoordinateDto {

    private Long id;
    private String postalCode;
    private String countryCode;
}
