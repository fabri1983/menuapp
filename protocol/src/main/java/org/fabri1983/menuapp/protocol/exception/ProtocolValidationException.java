package org.fabri1983.menuapp.protocol.exception;

import org.fabri1983.menuapp.protocol.validation.ValidationMessage;

public class ProtocolValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProtocolValidationException(ValidationMessage validationMessage) {
		super(String.format("%s. %s", validationMessage.getErrorId(), validationMessage.getMessage()));
	}
	
	public ProtocolValidationException(ValidationMessage validationMessage, String additionalMessage) {
		super(String.format("%s. %s. %s", validationMessage.getErrorId(), validationMessage.getMessage(), additionalMessage));
	}
}
