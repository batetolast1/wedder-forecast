package io.github.batetolast1.wedderforecast.dto.model.result;

import io.github.batetolast1.wedderforecast.dto.model.location.LocationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class RequestDailyResultDto {

    LocationDto locationDto;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future
    LocalDate localDate;
}
