package org.fabri1983.menuapp.core.converter.menu;

import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;

public class TimeConstraintMenuConverterStrategy implements MenuConverterStrategy {

	@Override
	public boolean acceptsMenu(DefaultMenu menu) {
		return false;
	}

	@Override
	public boolean acceptsMenu(TimeConstraintMenu menu) {
		return true;
	}

}
