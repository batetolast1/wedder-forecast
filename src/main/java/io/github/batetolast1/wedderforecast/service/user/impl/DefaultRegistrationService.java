package io.github.batetolast1.wedderforecast.service.user.impl;

import io.github.batetolast1.wedderforecast.model.user.Role;
import io.github.batetolast1.wedderforecast.model.user.User;
import io.github.batetolast1.wedderforecast.model.user.enums.UserRole;
import io.github.batetolast1.wedderforecast.repository.user.RoleRepository;
import io.github.batetolast1.wedderforecast.repository.user.UserRepository;
import io.github.batetolast1.wedderforecast.service.user.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

@RequiredArgsConstructor
public class DefaultRegistrationService implements RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(User user) {
        user.setActive(Boolean.TRUE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role roleUser = roleRepository.getByUserRole(UserRole.ROLE_USER);
        user.getRoles().add(roleUser);

        userRepository.save(user);
    }
}
