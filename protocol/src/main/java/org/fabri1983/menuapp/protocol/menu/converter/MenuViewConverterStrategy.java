package org.fabri1983.menuapp.protocol.menu.converter;

import org.fabri1983.menuapp.core.converter.menu.MenuTypeConverterRequester;
import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.protocol.menu.MenuView;

interface MenuViewConverterStrategy {

	MenuView convert(Menu menu);
	
	MenuTypeConverterRequester getStrategy();
}
