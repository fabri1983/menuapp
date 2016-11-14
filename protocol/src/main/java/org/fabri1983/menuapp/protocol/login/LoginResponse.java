package org.fabri1983.menuapp.protocol.login;

import org.fabri1983.menuapp.protocol.core.LocationPresentation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

	@JsonProperty
	private String userName;
	@JsonProperty
	private LocationPresentation location;
	@JsonProperty
	private String message;
	
	public LoginResponse(String userName, LocationPresentation location, String message) {
		this.userName = userName;
		this.location = location;
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public LocationPresentation getLocation() {
		return location;
	}
	
	public String getMessage() {
		return message;
	}
}
