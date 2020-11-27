package io.github.batetolast1.wedderforecast.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class RegistrationDataDto {

    @NotBlank
    @Size(min = 3, max = 16)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 16)
    private String password;
}
