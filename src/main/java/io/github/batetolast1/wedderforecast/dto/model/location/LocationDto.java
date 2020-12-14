package io.github.batetolast1.wedderforecast.dto.model.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LocationDto {

    private Long id;
    private String postalCode;
    private String countryCode;
    private String placeId;
    private String formattedAddress;
}
