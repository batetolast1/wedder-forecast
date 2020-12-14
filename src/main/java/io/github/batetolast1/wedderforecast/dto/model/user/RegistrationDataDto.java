package io.github.batetolast1.wedderforecast.dto.model.user;

import io.github.batetolast1.wedderforecast.validation.constraint.EmailValuesMatch;
import io.github.batetolast1.wedderforecast.validation.constraint.PasswordValuesMatch;
import io.github.batetolast1.wedderforecast.validation.constraint.UniqueEmail;
import io.github.batetolast1.wedderforecast.validation.constraint.UniqueUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@PasswordValuesMatch
@EmailValuesMatch

@NoArgsConstructor
@Getter
@Setter
public class RegistrationDataDto {

    @NotBlank
    @Size(min = 3, max = 16)
    @UniqueUsername
    private String username;

    @NotBlank
    @Email
    @UniqueEmail
    private String email;

    @NotBlank
    @Email
    private String verifyEmail;

    @NotBlank
    @Size(min = 3, max = 16)
    private String password;

    @NotBlank
    @Size(min = 3, max = 16)
    private String verifyPassword;

    @NotNull
    @AssertTrue
    private Boolean agreeTerms;
}
