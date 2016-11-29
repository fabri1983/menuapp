package org.fabri1983.menuapp.core.filtering.menu.decorator;

import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.core.filtering.menu.strategy.MenuFilterStrategy;

public class ChainedMenuFilterDecorator implements MenuFilterStrategy {

	MenuFilterStrategy nextFilter;
	MenuFilterStrategy decoratedFilter;
	
	public ChainedMenuFilterDecorator(MenuFilterStrategy filterDecorated) {
		this.decoratedFilter = filterDecorated;
	}

	@Override
	public boolean accepts(Menu menu) {
		if (!decoratedFilter.accepts(menu))
			return false;
		return nextFilter.accepts(menu);
	}

	public void setNextFilter(MenuFilterStrategy nextFilter) {
		this.nextFilter = nextFilter;
	}
	
}
