package io.github.batetolast1.wedderforecast.validation.constraint;

import io.github.batetolast1.wedderforecast.validation.validator.PasswordValuesMatchValidatorForRegistrationDataDto;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValuesMatchValidatorForRegistrationDataDto.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValuesMatch {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
