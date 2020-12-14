package io.github.batetolast1.wedderforecast.dto;

import io.github.batetolast1.wedderforecast.dto.model.location.LocationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class RequestGoogleMapsDailyResultDto {

    LocationDto locationDto;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate localDate;
}
