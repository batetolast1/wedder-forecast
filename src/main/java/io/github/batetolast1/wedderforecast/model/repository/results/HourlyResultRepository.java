package io.github.batetolast1.wedderforecast.model.repository.results;

import io.github.batetolast1.wedderforecast.model.entity.location.Location;
import io.github.batetolast1.wedderforecast.model.entity.results.HourlyResult;
import io.github.batetolast1.wedderforecast.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Repository
public interface HourlyResultRepository extends JpaRepository<HourlyResult, Long> {

    Optional<HourlyResult> findByUserAndLocationAndLocalDateTime(User user, Location persistedLocation, LocalDateTime localDateTime);

    Optional<HourlyResult> findByIdAndUser(Long id, User user);

    Set<HourlyResult> findAllByUser(User byUsername);
}
