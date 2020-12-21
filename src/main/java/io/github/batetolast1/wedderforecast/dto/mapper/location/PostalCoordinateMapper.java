package io.github.batetolast1.wedderforecast.dto.mapper.location;

import io.github.batetolast1.wedderforecast.dto.model.location.PostalCoordinateDto;
import io.github.batetolast1.wedderforecast.model.location.PostalCoordinate;
import org.springframework.stereotype.Service;

@Service
public class PostalCoordinateMapper {

    public PostalCoordinate toPostalCoordinate(PostalCoordinateDto postalCoordinateDto) {
        PostalCoordinate postalCoordinate = new PostalCoordinate();
        postalCoordinate.setPostalCode(postalCoordinateDto.getPostalCode());
        postalCoordinate.setCountryCode(postalCoordinateDto.getCountryCode());
        return postalCoordinate;
    }

    public PostalCoordinateDto toPostalCoordinateDto(PostalCoordinate postalCoordinate) {
        PostalCoordinateDto postalCoordinateDto = new PostalCoordinateDto();
        postalCoordinateDto.setId(postalCoordinate.getId());
        postalCoordinateDto.setPostalCode(postalCoordinate.getPostalCode());
        postalCoordinateDto.setCountryCode(postalCoordinate.getCountryCode());
        return postalCoordinateDto;
    }
}
