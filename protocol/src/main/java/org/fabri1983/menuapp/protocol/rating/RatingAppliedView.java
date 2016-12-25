package org.fabri1983.menuapp.protocol.rating;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.fabri1983.menuapp.core.entity.menu.Menu;

@ApiModel(value="RatingAppliedView", description="Presentation of a rating response")
@JsonNaming(LowerCaseStrategy.class)
public class RatingAppliedView {

	@ApiModelProperty(value = "menu id value", dataType = "long")
	@JsonProperty
	private long menuId;
	
	@ApiModelProperty(value = "rating value", dataType = "int")
	@JsonProperty
	private int rating;

	@JsonCreator // using @JsonCreator only for junit test
	public RatingAppliedView(
			@JsonProperty("menuId") long menuId,
			@JsonProperty("rating") int rating)
	{
		this.menuId = menuId;
		this.rating = rating;
	}
	
	public static RatingAppliedView create(Menu menuUpdated) {
		return new RatingAppliedView(menuUpdated.getId(), menuUpdated.getRating());
	}

	public long getMenuId() {
		return menuId;
	}

	public int getRating() {
		return rating;
	}

}
