package io.github.batetolast1.wedderforecast.repository.location;

import io.github.batetolast1.wedderforecast.model.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findFirstByPostalCodeAndCountryCode(String postalCode, String countryCode);

    Optional<Location> findByPlaceId(String placeId);
}
