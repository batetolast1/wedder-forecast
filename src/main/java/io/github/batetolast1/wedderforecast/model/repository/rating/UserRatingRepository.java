package io.github.batetolast1.wedderforecast.model.repository.rating;

import io.github.batetolast1.wedderforecast.model.entity.rating.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRatingRepository extends JpaRepository<UserRating, Long> {
}
