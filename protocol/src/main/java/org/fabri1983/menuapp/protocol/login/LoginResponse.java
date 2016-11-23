package org.fabri1983.menuapp.protocol.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

	@JsonProperty
	private String userName;
	@JsonProperty
	private LocationView location;
	@JsonProperty
	private String token;
	
	public LoginResponse(String userName, LocationView location, String token) {
		this.userName = userName;
		this.location = location;
		this.token = token;
	}
	
	public static LoginResponse create(LoginView loginRequest, String token) {
		return new LoginResponse(loginRequest.getUserName(), loginRequest.getLocation(), token);
	}

	public String getUserName () {
		return userName;
	}

	public LocationView getLocation () {
		return location;
	}
	
	public String getToken () {
		return token;
	}
}
