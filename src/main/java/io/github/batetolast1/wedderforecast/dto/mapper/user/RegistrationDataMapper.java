package io.github.batetolast1.wedderforecast.dto.mapper.user;

import io.github.batetolast1.wedderforecast.dto.model.user.RegistrationDataDto;
import io.github.batetolast1.wedderforecast.model.user.User;
import org.springframework.stereotype.Service;

@Service
public class RegistrationDataMapper {

    public User toUser(RegistrationDataDto registrationDataDto) {
        User user = new User();
        user.setUsername(registrationDataDto.getUsername());
        user.setEmail(registrationDataDto.getEmail());
        user.setPassword(registrationDataDto.getPassword());
        return user;
    }
}
