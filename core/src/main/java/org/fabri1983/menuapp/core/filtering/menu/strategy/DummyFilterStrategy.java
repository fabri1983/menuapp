package org.fabri1983.menuapp.core.filtering.menu.strategy;

import org.fabri1983.menuapp.core.entity.menu.Menu;

public class DummyFilterStrategy implements MenuFilterStrategy {

	@Override
	public boolean accepts(Menu menu) {
		return true;
	}

}
