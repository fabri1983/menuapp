package org.fabri1983.menuapp.core.view.converter;

import org.fabri1983.menuapp.core.menu.Menu;
import org.fabri1983.menuapp.core.view.MenuView;

public class MenuViewConverterResolver {

	public static MenuView convert (final Menu menu) {
		
		// first try other menu type than the default one
		if (menu.acceptsViewConverter(TimeConstraintMenuConverter.get()))
			return TimeConstraintMenuConverter.get().convert(menu);
		
		// fall back to the default menu
		return DefaultMenuConverter.get().convert(menu);
	}

}
