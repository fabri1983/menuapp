package org.fabri1983.menuapp.protocol.menu.converter;

import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.protocol.menu.MenuView;

public class MenuViewConverterResolver {

	public static MenuView convert(final Menu menu) {
		
		// first try other menu type than the default one
		if (menu.acceptsConverter(TimeConstraintMenuViewConverterStrategy.get().getStrategy())) {
			return TimeConstraintMenuViewConverterStrategy.get().convert(menu);
		}
		
		// fall back to the default menu
		return DefaultMenuViewConverterStrategy.get().convert(menu);
	}

}
