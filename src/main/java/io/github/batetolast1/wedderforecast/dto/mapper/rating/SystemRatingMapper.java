package io.github.batetolast1.wedderforecast.dto.mapper.rating;

import io.github.batetolast1.wedderforecast.dto.model.rating.SystemRatingDto;
import io.github.batetolast1.wedderforecast.model.rating.SystemRating;
import org.springframework.stereotype.Service;

@Service
public class SystemRatingMapper {

    public SystemRatingDto toSystemRatingDto(SystemRating systemRating) {
        SystemRatingDto systemRatingDto = new SystemRatingDto();
        systemRatingDto.setTemp(systemRating.getTemp());
        systemRatingDto.setFeelsLike(systemRating.getFeelsLike());
        systemRatingDto.setHeatIndex(systemRating.getHeatIndex());
        systemRatingDto.setMslPres(systemRating.getMslPres());
        systemRatingDto.setPrecip(systemRating.getPrecip());
        systemRatingDto.setSnowfall(systemRating.getSnowfall());
        systemRatingDto.setCldCvr(systemRating.getCldCvr());
        systemRatingDto.setWindSpd(systemRating.getWindSpd());
        systemRatingDto.setRelHum(systemRating.getRelHum());
        systemRatingDto.setOverall(systemRating.getOverall());
        return systemRatingDto;
    }
}
