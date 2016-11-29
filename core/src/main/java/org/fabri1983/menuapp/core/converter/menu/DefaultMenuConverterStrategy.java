package org.fabri1983.menuapp.core.converter.menu;

import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;

public class DefaultMenuConverterStrategy implements MenuConverterStrategy {

	@Override
	public boolean acceptsMenu(DefaultMenu menu) {
		return true;
	}

	@Override
	public boolean acceptsMenu(TimeConstraintMenu menu) {
		return false;
	}

}
