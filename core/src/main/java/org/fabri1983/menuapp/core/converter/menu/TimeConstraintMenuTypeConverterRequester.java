package org.fabri1983.menuapp.core.converter.menu;

import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;

public class TimeConstraintMenuTypeConverterRequester implements MenuTypeConverterRequester {

	@Override
	public boolean acceptsMenu(DefaultMenu menu) {
		return false;
	}

	@Override
	public boolean acceptsMenu(TimeConstraintMenu menu) {
		return true;
	}

}
