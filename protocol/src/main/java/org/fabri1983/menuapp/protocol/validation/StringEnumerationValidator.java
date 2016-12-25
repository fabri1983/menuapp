package org.fabri1983.menuapp.protocol.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringEnumerationValidator implements ConstraintValidator<StringEnumeration, String> {

	private Set<String> availableEnumNames;

	public static Set<String> getNamesSet(Class<? extends Enum<?>> enumParam) {
		Enum<?>[] enums = enumParam.getEnumConstants();
		String[] names = new String[enums.length];
		for (int i=0, c=enums.length; i < c; ++i) {
			names[i] = enums[i].name();
		}
		Set<String> mySet = new HashSet<String>(Arrays.asList(names));
		return mySet;
	}

	@Override
	public void initialize(StringEnumeration stringEnumeration) {
		Class<? extends Enum<?>> enumSelected = stringEnumeration.enumClass();
		availableEnumNames = getNamesSet(enumSelected);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		} else if (!availableEnumNames.contains(value)) {
			context.disableDefaultConstraintViolation();
	        context
	            .buildConstraintViolationWithTemplate("doesn't match with any of the enum values.")
	            .addConstraintViolation();
	        return false;
		}
		return true;
	}

}