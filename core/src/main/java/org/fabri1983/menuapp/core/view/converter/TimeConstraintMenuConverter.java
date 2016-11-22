package org.fabri1983.menuapp.core.view.converter;

import org.fabri1983.menuapp.core.menu.DefaultMenu;
import org.fabri1983.menuapp.core.menu.Menu;
import org.fabri1983.menuapp.core.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.core.view.MenuView;

public class TimeConstraintMenuConverter implements MenuViewConverter {

	@Override
	public MenuView convert(Menu menu) {
		// TODO investigate if is possible to use type inference using <T> since at the point
		// this method is executed by the MenuPresentationConverterResolver the subtype of menu is known.
		
		TimeConstraintMenu sourceMenu = (TimeConstraintMenu) menu;
		return new MenuView(
				sourceMenu.getId(),
				sourceMenu.getName(),
				sourceMenu.getDescription(),
				sourceMenu.getPictureUrl().toString(),
				sourceMenu.getPrice(),
				sourceMenu.getCurrency(),
				sourceMenu.getRating(),
				sourceMenu.getHourFrom(),
				sourceMenu.getHourTo(),
				sourceMenu.getAvailableDays(),
				sourceMenu.getAvailableDateFrom(),
				sourceMenu.getAvailableDateTo()
				);
	}

	@Override
	public boolean acceptsMenu(DefaultMenu menu) {
		return false;
	}

	@Override
	public boolean acceptsMenu(TimeConstraintMenu menu) {
		return true;
	}

}
