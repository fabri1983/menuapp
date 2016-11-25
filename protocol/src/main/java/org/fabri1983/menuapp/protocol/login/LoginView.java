package org.fabri1983.menuapp.protocol.login;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginView {
	
	@NotEmpty
	private String userName;
	@NotEmpty
	private String userPassHashed;
	@NotNull @Valid
	private LocationView location;

	@JsonCreator
	public LoginView(
			@JsonProperty("userName") String userName,
			@JsonProperty("userPassHashed") String userPassHashed,
			@JsonProperty("location") LocationView location)
	{
		this.userName = userName;
		this.userPassHashed = userPassHashed;
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
