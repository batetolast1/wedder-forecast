package io.github.batetolast1.wedderforecast.service.user;

public interface RegistrationValidationService {

    boolean isUniqueEmail(String email);

    boolean isUniqueUsername(String username);
}
