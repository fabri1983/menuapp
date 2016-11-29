package org.fabri1983.menuapp.core.service.implementation;

import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.core.repository.MenuRepository;
import org.fabri1983.menuapp.core.service.RatingService;

public class SimpleRatingService implements RatingService {

	MenuRepository menuRepository;
	
	public SimpleRatingService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Override
	public int getRatingFromMenu(long menuId) {
		Menu menu = menuRepository.getById(menuId);
		return menu.getRating();
	}

	@Override
	public Menu updateRating(long menuId, int rating) {
		Menu menu = menuRepository.getById(menuId);
		menu.updateRating(rating);
		return menuRepository.update(menu);
	}

}
