package io.github.batetolast1.wedderforecast.service.user.impl;

import io.github.batetolast1.wedderforecast.repository.user.UserRepository;
import io.github.batetolast1.wedderforecast.service.user.RegistrationValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class DefaultRegistrationValidationService implements RegistrationValidationService {

    private final UserRepository userRepository;

    @Override
    public boolean isUniqueEmail(String email) {
        return  !userRepository.existsByEmail(email);
    }

    @Override
    public boolean isUniqueUsername(String username) {
        return !userRepository.existsByUsername(username);
    }
}
