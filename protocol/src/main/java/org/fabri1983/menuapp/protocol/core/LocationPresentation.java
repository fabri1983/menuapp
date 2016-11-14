package org.fabri1983.menuapp.protocol.core;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationPresentation {

	@NotNull
	private BigInteger latitude;
	@NotNull
	private BigInteger longitude;

	@JsonCreator
	public LocationPresentation(
			@JsonProperty("latitude") BigInteger latitude, 
			@JsonProperty("longitude") BigInteger longitude) {
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
