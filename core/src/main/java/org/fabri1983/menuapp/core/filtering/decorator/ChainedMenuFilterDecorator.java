package org.fabri1983.menuapp.core.filtering.decorator;

import org.fabri1983.menuapp.core.filtering.MenuFilter;
import org.fabri1983.menuapp.core.menu.Menu;

public class ChainedMenuFilterDecorator implements MenuFilter {

	MenuFilter nextFilter;
	MenuFilter decoratedFilter;
	
	public ChainedMenuFilterDecorator(MenuFilter filterDecorated) {
		this.decoratedFilter = filterDecorated;
	}

	@Override
	public boolean accepts(Menu menu) {
		if (!decoratedFilter.accepts(menu))
			return false;
		return nextFilter.accepts(menu);
	}

	public void setNextFilter(MenuFilter nextFilter) {
		this.nextFilter = nextFilter;
	}
	
}
