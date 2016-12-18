package org.fabri1983.menuapp.protocol.login;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="LoginView", description="Presentation of a login request")
public class LoginView {
	
	@ApiModelProperty(value = "userName value", dataType = "String", required = true)
	@NotEmpty
	private String userName;
	
	@ApiModelProperty(value = "userPassHashed value", dataType = "String", required = true)
	@NotEmpty
	private String userPassHashed;
	
	@ApiModelProperty(value = "location value", dataType = "org.fabri1983.menuapp.protocol.login.LocationView", required = true)
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
