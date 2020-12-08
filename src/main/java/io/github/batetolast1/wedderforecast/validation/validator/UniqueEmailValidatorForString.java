package io.github.batetolast1.wedderforecast.validation.validator;

import io.github.batetolast1.wedderforecast.service.ValidationService;
import io.github.batetolast1.wedderforecast.validation.constraint.UniqueEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component

@RequiredArgsConstructor
public class UniqueEmailValidatorForString implements ConstraintValidator<UniqueEmail, String> {

    private final ValidationService validationService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validationService.isUniqueEmail(value);
    }
}
