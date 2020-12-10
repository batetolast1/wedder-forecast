package io.github.batetolast1.wedderforecast.model.repository.results;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.results.SimpleResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SimpleResultRepository extends JpaRepository<SimpleResult, Long> {

    Optional<SimpleResult> findByLocationAndLocalDateTime(Location location, LocalDateTime localDateTime);
}
