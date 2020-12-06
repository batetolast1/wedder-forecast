package io.github.batetolast1.wedderforecast.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class RequestGoogleMapsSimpleResultDto {

    LocationDto locationDto;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate localDate;
}
