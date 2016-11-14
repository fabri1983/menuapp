package org.fabri1983.menuapp.protocol.login;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.fabri1983.menuapp.protocol.core.LocationPresentation;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {

	@NotEmpty
	private String userName;
	@NotEmpty
	private String userPassHashed;
	@NotNull
	private LocationPresentation location;

	@JsonCreator
	public LoginRequest(
			@JsonProperty("userName") String userName,
			@JsonProperty("userPassHashed") String userPassHashed,
			@JsonProperty("location") @Valid LocationPresentation location)
	{
		this.location = location;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getUserPassHashed() {
		return userPassHashed;
	}
	
	public LocationPresentation getLocation() {
		return location;
	}

}
