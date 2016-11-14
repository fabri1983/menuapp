package org.fabri1983.menuapp.protocol.rating;

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

	public long getMenuId() {
		return menuId;
	}

	public int getRating() {
		return rating;
	}

}
