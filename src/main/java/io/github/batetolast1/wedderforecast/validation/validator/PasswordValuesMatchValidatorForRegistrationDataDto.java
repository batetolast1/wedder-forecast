package io.github.batetolast1.wedderforecast.validation.validator;

import io.github.batetolast1.wedderforecast.dto.RegistrationDataDto;
import io.github.batetolast1.wedderforecast.validation.constraint.PasswordValuesMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValuesMatchValidatorForRegistrationDataDto implements ConstraintValidator<PasswordValuesMatch, RegistrationDataDto> {

    public boolean isValid(RegistrationDataDto registrationDataDto, ConstraintValidatorContext context) {
        if (registrationDataDto.getPassword() == null || registrationDataDto.getVerifyPassword() == null) {
            return true;
        }

        boolean valid = registrationDataDto.getPassword().equals(registrationDataDto.getVerifyPassword());
        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{error.message.password-values-match}")
                    .addPropertyNode("verifyPassword")
                    .addConstraintViolation();
        }
        return valid;
    }
}
