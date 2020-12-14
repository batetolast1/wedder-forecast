package io.github.batetolast1.wedderforecast.service.result;

import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.results.DailyResult;

import java.time.LocalDateTime;
import java.util.List;

public interface DailyResultService {

    DailyResult getDailyResult(Location location, LocalDateTime localDateTime);

    DailyResult getDailyResult(Long id);

    List<DailyResult> getLatestDailyResults();

    List<DailyResult> getAllDailyResults();
}
