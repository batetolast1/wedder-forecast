package io.github.batetolast1.wedderforecast.service.result;

import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.results.HourlyResult;

import java.time.LocalDateTime;
import java.util.List;

public interface HourlyResultService {

    HourlyResult getHourlyResult(Location location, LocalDateTime localDateTime);

    HourlyResult getHourlyResult(Long id);

    List<HourlyResult> getLatestHourlyResults();

    List<HourlyResult> getAllHourlyResults();
}
