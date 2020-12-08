package io.github.batetolast1.wedderforecast.validation.constraint;

import io.github.batetolast1.wedderforecast.validation.validator.UniqueUsernameValidatorForString;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUsernameValidatorForString.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {

    String message() default "{error.message.unique-username}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
