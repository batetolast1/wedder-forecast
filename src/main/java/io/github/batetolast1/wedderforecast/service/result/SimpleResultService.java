package io.github.batetolast1.wedderforecast.service.result;

import io.github.batetolast1.wedderforecast.dto.RequestGoogleMapsDailyResultDto;
import io.github.batetolast1.wedderforecast.dto.ResponseSimpleResultDto;

public interface SimpleResultService {

    ResponseSimpleResultDto getSimpleSearchResultFromGoogleMaps(RequestGoogleMapsDailyResultDto requestGoogleMapsDailyResultDto);
}
