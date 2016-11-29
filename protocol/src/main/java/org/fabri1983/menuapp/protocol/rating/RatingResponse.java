package org.fabri1983.menuapp.protocol.rating;

import org.fabri1983.menuapp.core.entity.menu.Menu;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RatingResponse {

	@JsonProperty
	private long menuId;
	@JsonProperty
	private int rating;

	public RatingResponse(long menuId, int rating) {
		this.menuId = menuId;
		this.rating = rating;
	}
	
	public static RatingResponse create(Menu menuUpdated) {
		return new RatingResponse(menuUpdated.getId(), menuUpdated.getRating());
	}

	public long getMenuId() {
		return menuId;
	}

	public int getRating() {
		return rating;
	}

}
