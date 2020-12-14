package io.github.batetolast1.wedderforecast.repository.rating;

import io.github.batetolast1.wedderforecast.model.rating.SystemRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRatingRepository extends JpaRepository<SystemRating, Long> {
}
