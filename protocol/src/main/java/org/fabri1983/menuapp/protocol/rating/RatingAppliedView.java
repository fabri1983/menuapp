package org.fabri1983.menuapp.protocol.rating;

import org.fabri1983.menuapp.core.entity.menu.Menu;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RatingAppliedView {

	@JsonProperty
	private long menuId;
	@JsonProperty
	private int rating;

	public RatingAppliedView(long menuId, int rating) {
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
