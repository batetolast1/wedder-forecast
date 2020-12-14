package io.github.batetolast1.wedderforecast.validation.validator;

import io.github.batetolast1.wedderforecast.dto.model.user.RegistrationDataDto;
import io.github.batetolast1.wedderforecast.validation.constraint.EmailValuesMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValuesMatchValidatorForRegistrationDataDto implements ConstraintValidator<EmailValuesMatch, RegistrationDataDto> {

    public boolean isValid(RegistrationDataDto registrationDataDto, ConstraintValidatorContext context) {
        if (registrationDataDto.getEmail() == null || registrationDataDto.getVerifyEmail() == null) {
            return true;
        }

        boolean valid = registrationDataDto.getEmail().equals(registrationDataDto.getVerifyEmail());
        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{error.message.email-values-match}")
                    .addPropertyNode("verifyEmail")
                    .addConstraintViolation();
        }
        return valid;
    }
}
