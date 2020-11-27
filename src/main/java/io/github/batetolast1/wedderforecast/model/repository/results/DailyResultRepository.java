package io.github.batetolast1.wedderforecast.model.repository.results;

import io.github.batetolast1.wedderforecast.model.entity.results.DailyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyResultRepository extends JpaRepository<DailyResult, Long> {
}
