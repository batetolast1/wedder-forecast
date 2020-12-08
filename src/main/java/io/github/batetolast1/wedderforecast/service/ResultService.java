package io.github.batetolast1.wedderforecast.service;

import io.github.batetolast1.wedderforecast.dto.RequestFormSimpleResultDto;
import io.github.batetolast1.wedderforecast.dto.ResponseSimpleResultDto;
import io.github.batetolast1.wedderforecast.dto.RequestGoogleMapsDailyResultDto;

public interface ResultService {

    ResponseSimpleResultDto getSimpleSearchResultFromForm(RequestFormSimpleResultDto requestFormSimpleResultDto);

    ResponseSimpleResultDto getSimpleSearchResultFromGoogleMaps(RequestGoogleMapsDailyResultDto requestGoogleMapsDailyResultDto);
}
