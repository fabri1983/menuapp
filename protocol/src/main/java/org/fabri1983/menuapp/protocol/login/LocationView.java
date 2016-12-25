package org.fabri1983.menuapp.protocol.login;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;

@ApiModel(value="LocationView", description="Presentation of a location request/response")
@JsonNaming(LowerCaseStrategy.class)
public class LocationView {
	
	@ApiModelProperty(value = "latitude value", dataType = "int", required = true)
	@NotNull
	private BigInteger latitude;
	
	@ApiModelProperty(value = "longitude value", dataType = "int", required = true)
	@NotNull
	private BigInteger longitude;

	@JsonCreator
	public LocationView(
			@JsonProperty("latitude") BigInteger latitude, 
			@JsonProperty("longitude") BigInteger longitude)
	{
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public BigInteger getLatitude() {
		return latitude;
	}

	public BigInteger getLongitude() {
		return longitude;
	}

}
