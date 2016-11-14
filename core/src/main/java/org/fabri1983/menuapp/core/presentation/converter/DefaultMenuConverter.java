package org.fabri1983.menuapp.core.presentation.converter;

import org.fabri1983.menuapp.core.menu.DefaultMenu;
import org.fabri1983.menuapp.core.menu.Menu;
import org.fabri1983.menuapp.core.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.core.presentation.MenuPresentation;

public class DefaultMenuConverter implements MenuPresentationConverter {

	@Override
	public MenuPresentation convert(Menu menu) {
		// TODO investigate if is possible to use type inference using <T> since at the point
		// this method is executed by the MenuPresentationConverterResolver the subtype of menu is known.
		
		DefaultMenu sourceMenu = (DefaultMenu) menu;
		// FIXME ugly constructor with lots of null! Segregate better!
		return new MenuPresentation(
				sourceMenu.getId(),
				sourceMenu.getName(),
				sourceMenu.getDescription(),
				sourceMenu.getPictureUrl().toString(),
				sourceMenu.getPrice(),
				sourceMenu.getCurrency(),
				sourceMenu.getRating(),
				null,
				null,
				null,
				null,
				null
				);
	}

	@Override
	public boolean acceptsMenu(DefaultMenu menu) {
		return true;
	}

	@Override
	public boolean acceptsMenu(TimeConstraintMenu menu) {
		return false;
	}

}
