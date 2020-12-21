package io.github.batetolast1.wedderforecast.repository.results;

import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.results.SimpleDailyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SimpleDailyResultRepository extends JpaRepository<SimpleDailyResult, Long> {

    Optional<SimpleDailyResult> findByLocationAndLocalDateTime(Location location, LocalDateTime localDateTime);
}
