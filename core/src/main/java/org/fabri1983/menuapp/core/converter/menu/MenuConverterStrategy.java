package org.fabri1983.menuapp.core.converter.menu;

import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;

public interface MenuConverterStrategy {

	boolean acceptsMenu (DefaultMenu menu);
	
	boolean acceptsMenu (TimeConstraintMenu menu);
}
