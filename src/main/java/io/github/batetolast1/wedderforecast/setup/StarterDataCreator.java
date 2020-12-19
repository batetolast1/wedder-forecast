package io.github.batetolast1.wedderforecast.setup;

import io.github.batetolast1.wedderforecast.model.user.Role;
import io.github.batetolast1.wedderforecast.model.user.User;
import io.github.batetolast1.wedderforecast.model.user.enums.UserRole;
import io.github.batetolast1.wedderforecast.repository.user.RoleRepository;
import io.github.batetolast1.wedderforecast.service.user.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile({"heroku"})

@RequiredArgsConstructor
public class StarterDataCreator implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final RegistrationService registrationService;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        createRoles();
        registerTestUser();
    }

    private void createRoles() {
        Role userRole = new Role();
        userRole.setUserRole(UserRole.ROLE_USER);
        roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setUserRole(UserRole.ROLE_ADMIN);
        roleRepository.save(adminRole);
    }

    private void registerTestUser() {
        User user = new User();
        user.setUsername("user");
        user.setEmail("user@user.mail");
        user.setPassword("password");
        registrationService.register(user);
    }
}
