package org.fabri1983.menuapp.protocol.login;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginView {
	
	private String userName;
	private String userPassHashed;
	private LocationView location;

	@JsonCreator
	public LoginView(
			@JsonProperty("userName") @NotEmpty String userName,
			@JsonProperty("userPassHashed") @NotEmpty String userPassHashed,
			@JsonProperty("location") @NotNull @Valid LocationView location)
	{
		this.location = location;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getUserPassHashed() {
		return userPassHashed;
	}
	
	public LocationView getLocation() {
		return location;
	}

}
