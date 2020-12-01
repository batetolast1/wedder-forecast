package io.github.batetolast1.wedderforecast.dto;

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
}
