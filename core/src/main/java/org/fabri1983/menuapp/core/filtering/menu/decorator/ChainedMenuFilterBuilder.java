package org.fabri1983.menuapp.core.filtering.menu.decorator;

import org.fabri1983.menuapp.core.filtering.menu.strategy.DummyFilterStrategy;
import org.fabri1983.menuapp.core.filtering.menu.strategy.MenuFilterStrategy;

public class ChainedMenuFilterBuilder {

	private ChainedMenuFilterDecorator first;
	private ChainedMenuFilterDecorator current;
	
	ChainedMenuFilterBuilder () {
		current = new ChainedMenuFilterDecorator(new DummyFilterStrategy());
		first = current;
	}
	
	public static ChainedMenuFilterBuilder newOne () {
		return new ChainedMenuFilterBuilder();
	}
	
	public ChainedMenuFilterBuilder chain (MenuFilterStrategy menuFilter) {
		
		// decorate the filter so it can handle a next filter
		ChainedMenuFilterDecorator next = new ChainedMenuFilterDecorator(menuFilter);
		
		// modify actual chain filter setting its next filter
		current.setNextFilter(next);
		
		// move forward to the new next filter so
		current = next;
		
		return this;
	}
	
	public MenuFilterStrategy build () {
		// filter closure: make sure no null filter is left in the chain
		current.setNextFilter(new DummyFilterStrategy());
		
		return first;
	}
}
