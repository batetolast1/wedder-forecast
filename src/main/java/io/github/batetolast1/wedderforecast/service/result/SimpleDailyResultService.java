package io.github.batetolast1.wedderforecast.service.result;

import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.results.SimpleDailyResult;

import java.time.LocalDateTime;

public interface SimpleDailyResultService {

    SimpleDailyResult getSimpleDailyResult(Location location, LocalDateTime localDateTime);

    SimpleDailyResult getSimpleDailyResult(Long id);
}
