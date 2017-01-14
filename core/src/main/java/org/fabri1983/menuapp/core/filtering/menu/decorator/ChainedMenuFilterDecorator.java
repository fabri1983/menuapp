package org.fabri1983.menuapp.core.filtering.menu.decorator;

import org.fabri1983.menuapp.core.filtering.menu.MenuFilterable;
import org.fabri1983.menuapp.core.filtering.menu.strategy.MenuFilterStrategy;

public class ChainedMenuFilterDecorator implements MenuFilterStrategy {

	MenuFilterStrategy nextFilter;
	MenuFilterStrategy decoratedFilter;
	
	public ChainedMenuFilterDecorator(MenuFilterStrategy filterDecorated) {
		this.decoratedFilter = filterDecorated;
	}

	@Override
	public boolean filter(MenuFilterable menu) {
		if (!decoratedFilter.filter(menu))
			return false;
		return nextFilter.filter(menu);
	}

	public void setNextFilter(MenuFilterStrategy nextFilter) {
		this.nextFilter = nextFilter;
	}
	
}
