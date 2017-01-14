package org.fabri1983.menuapp.protocol.menu.factory;

import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.protocol.menu.MenuView;

public class MenuViewFactory {

	public static MenuView from(final DefaultMenu menu) {
		MenuView menuView = new MenuView();
		menuView.setId(menu.getId());
		menuView.setName(menu.getName());
		menuView.setDescription(menu.getDescription());
		menuView.setPictureUrl(menu.getPictureUrl().toString()); 
		menuView.setPrice(menu.getPrice());
		menuView.setCurrency(menu.getCurrency());
		menuView.setRating(menu.getRating());
		return menuView;
	}
	
	public static MenuView from(final TimeConstraintMenu menu) {
		MenuView menuView = new MenuView();
		menuView.setId(menu.getId());
		menuView.setName(menu.getName());
		menuView.setDescription(menu.getDescription());
		menuView.setPictureUrl(menu.getPictureUrl().toString()); 
		menuView.setPrice(menu.getPrice());
		menuView.setCurrency(menu.getCurrency());
		menuView.setRating(menu.getRating());
		menuView.setHourFrom(menu.getHourFrom());
		menuView.setHourTo(menu.getHourTo());
		menuView.setAvailableDays(menu.getAvailableDays());
		menuView.setAvailableDateFrom(menu.getAvailableDateFrom());
		menuView.setAvailableDateTo(menu.getAvailableDateTo());
		return menuView;
	}
}
