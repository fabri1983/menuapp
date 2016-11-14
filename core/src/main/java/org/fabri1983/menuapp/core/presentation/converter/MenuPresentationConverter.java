package org.fabri1983.menuapp.core.presentation.converter;

import org.fabri1983.menuapp.core.menu.DefaultMenu;
import org.fabri1983.menuapp.core.menu.Menu;
import org.fabri1983.menuapp.core.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.core.presentation.MenuPresentation;

public interface MenuPresentationConverter {

	MenuPresentation convert (Menu menu);
	
	boolean acceptsMenu (DefaultMenu menu);
	
	boolean acceptsMenu (TimeConstraintMenu menu);
}
