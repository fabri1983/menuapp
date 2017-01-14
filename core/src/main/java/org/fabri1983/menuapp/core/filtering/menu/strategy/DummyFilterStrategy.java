package org.fabri1983.menuapp.core.filtering.menu.strategy;

import org.fabri1983.menuapp.core.filtering.menu.MenuFilterable;

public class DummyFilterStrategy implements MenuFilterStrategy {

	@Override
	public boolean filter(MenuFilterable menu) {
		return true;
	}

}
