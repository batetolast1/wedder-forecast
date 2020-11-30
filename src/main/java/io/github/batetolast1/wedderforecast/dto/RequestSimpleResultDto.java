package io.github.batetolast1.wedderforecast.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestSimpleResultDto {

    private String countryCode;
    private String postalCode;
    private int day;
    private int month;
    private int year;
}
