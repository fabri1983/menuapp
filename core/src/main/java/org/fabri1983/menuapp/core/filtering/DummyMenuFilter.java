package org.fabri1983.menuapp.core.filtering;

import org.fabri1983.menuapp.core.menu.Menu;

public class DummyMenuFilter implements MenuFilter {

	@Override
	public boolean accepts(Menu menu) {
		return true;
	}

}
