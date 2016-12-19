package org.fabri1983.menuapp.protocol.login;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="LoginSuccessfulView", description="Presentation of a successful login")
@JsonNaming(LowerCaseStrategy.class)
public class LoginSuccessfulView {

	@ApiModelProperty(value = "userName value", dataType = "String")
	@JsonProperty
	private String userName;
	
	@ApiModelProperty(value = "location value", dataType = "org.fabri1983.menuapp.protocol.login.LocationView")
	@JsonProperty
	private LocationView location;
	
	@ApiModelProperty(value = "token value", dataType = "String")
	@JsonProperty
	private String token;
	
	@JsonCreator // using @JsonCreator only for junit test
	public LoginSuccessfulView(
			@JsonProperty("userName") String userName,
			@JsonProperty("location") LocationView location,
			@JsonProperty("token") String token)
	{
		this.userName = userName;
		this.location = location;
		this.token = token;
	}
	
	public static LoginSuccessfulView create(LoginView loginRequest, String token) {
		return new LoginSuccessfulView(loginRequest.getUserName(), loginRequest.getLocation(), token);
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
