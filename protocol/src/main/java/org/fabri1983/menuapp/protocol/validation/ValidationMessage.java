package org.fabri1983.menuapp.protocol.validation;

public enum ValidationMessage {

	NO_CONVERTER_FOR_THE_MENU(1000, "No converter for the menu");
	
	private int errorId;
	private String message;
	
	ValidationMessage(int errorId, String message) {
		this.errorId = errorId;
		this.message = message;
	}

	public int getErrorId() {
		return errorId;
	}

	public String getMessage() {
		return message;
	}
	
}
