package io.github.batetolast1.wedderforecast.repository.location;

import io.github.batetolast1.wedderforecast.model.location.PostalCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostalCoordinateRepository extends JpaRepository<PostalCoordinate, Long> {

    Optional<PostalCoordinate> findByPostalCodeAndCountryCode(String postalCode, String countryCode);
}
