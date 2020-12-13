package io.github.batetolast1.wedderforecast.service.impl;

import io.github.batetolast1.wedderforecast.dto.RegistrationDataDto;
import io.github.batetolast1.wedderforecast.model.entity.user.Role;
import io.github.batetolast1.wedderforecast.model.entity.user.User;
import io.github.batetolast1.wedderforecast.model.entity.user.enums.UserRole;
import io.github.batetolast1.wedderforecast.model.repository.user.RoleRepository;
import io.github.batetolast1.wedderforecast.model.repository.user.UserRepository;
import io.github.batetolast1.wedderforecast.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

@RequiredArgsConstructor
public class DefaultRegistrationService implements RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegistrationDataDto registrationData) {
        // TODO .trim() fields!
        User user = modelMapper.map(registrationData, User.class);
        user.setActive(Boolean.TRUE);

        String encodedPassword = passwordEncoder.encode(registrationData.getPassword());
        user.setPassword(encodedPassword);

        Role roleUser = roleRepository.getByUserRole(UserRole.ROLE_USER);
        user.getRoles().add(roleUser);

        userRepository.save(user);
    }
}
