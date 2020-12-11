package io.github.batetolast1.wedderforecast.model.repository.results;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.results.DailyResult;
import io.github.batetolast1.wedderforecast.model.entity.user.User;
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
