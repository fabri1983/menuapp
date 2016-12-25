package org.fabri1983.menuapp.core.converter.menu;

import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;

/**
 * This interface created to separate conversion decision from the classes where indeed conversion takes place, so the core doesn't know anything about specific converters.
 * Right now only on protocol project there is a menu-to-view converter. 
 * So this interface avoids the need to implement the converter in the core project and implementation only lies in protocol project.
 */
public interface MenuTypeConverterRequester {

	boolean acceptsMenu(DefaultMenu menu);
	
	boolean acceptsMenu(TimeConstraintMenu menu);
}
