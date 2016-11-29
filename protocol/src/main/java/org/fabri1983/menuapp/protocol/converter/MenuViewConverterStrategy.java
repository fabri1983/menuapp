package org.fabri1983.menuapp.protocol.converter;

import org.fabri1983.menuapp.core.converter.menu.MenuTypeConverterRequester;
import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.protocol.menu.MenuView;

public interface MenuViewConverterStrategy {

	MenuView convert (Menu menu);
	
	MenuTypeConverterRequester getStrategy ();
}
