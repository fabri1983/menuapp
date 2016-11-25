package org.fabri1983.menuapp.protocol.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = StringEnumerationValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER, CONSTRUCTOR })
@Retention(RUNTIME)
public @interface StringEnumeration {

	String message() default "{com.xxx.bean.validation.constraints.StringEnumeration.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<? extends Enum<?>> enumClass();

}
