package io.github.batetolast1.wedderforecast.config.property;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "api.weathersource")
@ConstructorBinding

@Getter
public class WeatherSourceApiProperties {

    private final String apiKey;
    private final String host;
    private final String path;
    private final String timestampQuery;
    private final String fieldsQuery;
    private final String dailyFields;
    private final String hourlyFields;
    private final String periodQuery;

    public WeatherSourceApiProperties(String apiKey,
                                      String host,
                                      String path,
                                      String timestampQuery,
                                      String fieldsQuery,
                                      String dailyFields,
                                      String hourlyFields,
                                      String periodQuery) {
        this.apiKey = apiKey;
        this.host = host;
        this.path = path;
        this.timestampQuery = timestampQuery;
        this.fieldsQuery = fieldsQuery;
        this.dailyFields = dailyFields;
        this.hourlyFields = hourlyFields;
        this.periodQuery = periodQuery;
    }
}
