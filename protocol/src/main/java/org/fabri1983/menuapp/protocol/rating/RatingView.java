package org.fabri1983.menuapp.protocol.rating;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="RatingView", description="Presentation of a rating request")
@JsonNaming(LowerCaseStrategy.class)
public class RatingView {

	@ApiModelProperty(value = "rating value between 1 and 5", dataType = "int", allowableValues = "range[1, 5]", required = true)
	@Min(1) @Max(5)
	private int rating;
	
	@ApiModelProperty(value = "description value", dataType = "String", allowableValues = "Text between 5 and 255 chars", required = true)
	@Length(min = 5, max = 255)
	@NotEmpty
	private String description;
	
	@JsonCreator
	public RatingView(
			@JsonProperty("rating") int rating, 
			@JsonProperty("description") String description)
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
