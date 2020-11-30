package io.github.batetolast1.wedderforecast.config;

import io.github.batetolast1.wedderforecast.dto.PredictedDailyWeatherDto;
import io.github.batetolast1.wedderforecast.dto.ResponseSimpleResultDto;
import io.github.batetolast1.wedderforecast.model.entity.results.SimpleResult;
import io.github.batetolast1.wedderforecast.model.entity.weather.PredictedDailyWeather;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        TypeMap<SimpleResult, ResponseSimpleResultDto> responseSimpleResultDtoTypeMap = modelMapper.createTypeMap(SimpleResult.class, ResponseSimpleResultDto.class);
        responseSimpleResultDtoTypeMap.addMapping(SimpleResult::getLocation, ResponseSimpleResultDto::setLocationDto);
        responseSimpleResultDtoTypeMap.addMapping(SimpleResult::getPredictedDailyWeather, ResponseSimpleResultDto::setPredictedDailyWeatherDto);

        TypeMap<PredictedDailyWeather, PredictedDailyWeatherDto> predictedDailyWeatherDtoTypeMap = modelMapper.createTypeMap(PredictedDailyWeather.class, PredictedDailyWeatherDto.class);
        predictedDailyWeatherDtoTypeMap.addMapping(PredictedDailyWeather::getSystemRating, PredictedDailyWeatherDto::setSystemRatingDto);

        return modelMapper;
    }
}
