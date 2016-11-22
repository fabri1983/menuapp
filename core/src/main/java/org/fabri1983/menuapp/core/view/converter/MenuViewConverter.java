package org.fabri1983.menuapp.core.view.converter;

import org.fabri1983.menuapp.core.menu.DefaultMenu;
import org.fabri1983.menuapp.core.menu.Menu;
import org.fabri1983.menuapp.core.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.core.view.MenuView;

public interface MenuViewConverter {

	MenuView convert (Menu menu);
	
	boolean acceptsMenu (DefaultMenu menu);
	
	boolean acceptsMenu (TimeConstraintMenu menu);
}
