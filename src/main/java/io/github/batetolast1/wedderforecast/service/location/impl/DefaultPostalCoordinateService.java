package io.github.batetolast1.wedderforecast.service.location.impl;

import io.github.batetolast1.wedderforecast.model.location.PostalCoordinate;
import io.github.batetolast1.wedderforecast.repository.location.PostalCoordinateRepository;
import io.github.batetolast1.wedderforecast.service.location.PostalCoordinateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional

@Log4j2
@RequiredArgsConstructor
public class DefaultPostalCoordinateService implements PostalCoordinateService {

    private final PostalCoordinateRepository postalCoordinateRepository;

    @Override
    public PostalCoordinate getPersistedPostalCoordinate(PostalCoordinate postalCoordinate) {
        Optional<PostalCoordinate> optionalPostalCoordinate = postalCoordinateRepository.findByPostalCodeAndCountryCode(postalCoordinate.getPostalCode(), postalCoordinate.getCountryCode());

        if (optionalPostalCoordinate.isPresent()) {
            log.debug("Postal coordinate found in DB");
            return optionalPostalCoordinate.get();
        }

        log.debug("Postal coordinate not found in DB");
        return postalCoordinateRepository.save(postalCoordinate);
    }
}
