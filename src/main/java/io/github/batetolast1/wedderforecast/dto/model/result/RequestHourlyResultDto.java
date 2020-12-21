package io.github.batetolast1.wedderforecast.dto.model.result;

import io.github.batetolast1.wedderforecast.dto.model.location.LocationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class RequestHourlyResultDto {

    LocationDto locationDto;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Future
    LocalDateTime localDateTime;
}
