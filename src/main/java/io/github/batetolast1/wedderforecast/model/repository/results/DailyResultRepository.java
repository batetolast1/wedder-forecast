package io.github.batetolast1.wedderforecast.model.repository.results;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.results.DailyResult;
import io.github.batetolast1.wedderforecast.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DailyResultRepository extends JpaRepository<DailyResult, Long> {

    Optional<DailyResult> findByUserAndLocationAndLocalDate(User user, Location location, LocalDate localDate);

    Optional<DailyResult> findByIdAndUser(Long id, User user);

    Set<DailyResult> findAllByUser(User user);
}
