package org.fabri1983.menuapp.protocol.rating;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RatingView {

	private int rating;
	private String description;
	
	@JsonCreator
	public RatingView(
			@JsonProperty("rating") @Min(1) @Max(5) int rating, 
			@JsonProperty("description") @NotEmpty String description)
	{
		this.rating = rating;
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public String getDescription() {
		return description;
	}
}
