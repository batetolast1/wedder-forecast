package io.github.batetolast1.wedderforecast.repository.results;

import io.github.batetolast1.wedderforecast.model.location.Location;
import io.github.batetolast1.wedderforecast.model.results.DailyResult;
import io.github.batetolast1.wedderforecast.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DailyResultRepository extends JpaRepository<DailyResult, Long> {

    Optional<DailyResult> findByUserAndLocationAndLocalDateTime(User user, Location location, LocalDateTime localDateTime);

    Optional<DailyResult> findByIdAndUser(Long id, User user);

    Set<DailyResult> findTop3ByUserOrderByCreatedOnDesc(User user);

    Set<DailyResult> findAllByUser(User user);
}
