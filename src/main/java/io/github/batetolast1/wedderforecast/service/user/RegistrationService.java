package io.github.batetolast1.wedderforecast.service.user;

import io.github.batetolast1.wedderforecast.dto.model.user.RegistrationDataDto;

public interface RegistrationService {

    void register(RegistrationDataDto registrationData);
}
