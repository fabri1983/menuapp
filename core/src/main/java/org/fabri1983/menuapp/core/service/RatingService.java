package org.fabri1983.menuapp.core.service;

import org.fabri1983.menuapp.core.entity.menu.Menu;

public interface RatingService {

	int getRatingFromMenu (long menuId);
	
	Menu updateRating (long menuId, int rating);
}
