package io.github.batetolast1.wedderforecast.service;

import io.github.batetolast1.wedderforecast.dto.RequestSimpleResultDto;
import io.github.batetolast1.wedderforecast.dto.ResponseSimpleResultDto;

public interface ResultService {

    ResponseSimpleResultDto getSimpleSearchResult(RequestSimpleResultDto requestSimpleResultDto);
}
