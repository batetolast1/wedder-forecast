package io.github.batetolast1.wedderforecast.model.repository.results;

import io.github.batetolast1.wedderforecast.model.entity.results.HourlyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HourlyResultRepository extends JpaRepository<HourlyResult, Long> {
}
