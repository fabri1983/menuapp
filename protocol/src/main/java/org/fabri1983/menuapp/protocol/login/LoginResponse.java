package org.fabri1983.menuapp.protocol.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

	@JsonProperty
	private String userName;
	@JsonProperty
	private LocationView location;
	@JsonProperty
	private String message;
	
	public LoginResponse(String userName, LocationView location, String message) {
		this.userName = userName;
		this.location = location;
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public LocationView getLocation() {
		return location;
	}
	
	public String getMessage() {
		return message;
	}
}
