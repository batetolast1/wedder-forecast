package io.github.batetolast1.wedderforecast.dto.model.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LocationDto {

    // TODO add Google Maps validation
    private Long id;
    private String placeId;
    private String formattedAddress;
    private String name;
    private PostalCoordinateDto postalCoordinateDto;
}
