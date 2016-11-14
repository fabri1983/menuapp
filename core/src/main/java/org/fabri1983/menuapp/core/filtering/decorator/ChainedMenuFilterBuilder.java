package org.fabri1983.menuapp.core.filtering.decorator;

import org.fabri1983.menuapp.core.filtering.DummyMenuFilter;
import org.fabri1983.menuapp.core.filtering.MenuFilter;

public class ChainedMenuFilterBuilder {

	private ChainedMenuFilterDecorator first;
	private ChainedMenuFilterDecorator current;
	
	ChainedMenuFilterBuilder () {
		current = new ChainedMenuFilterDecorator(new DummyMenuFilter());
		first = current;
	}
	
	public static ChainedMenuFilterBuilder newOne () {
		return new ChainedMenuFilterBuilder();
	}
	
	public ChainedMenuFilterBuilder chain (MenuFilter menuFilter) {
		
		// decorate the filter so it can handle a next filter
		ChainedMenuFilterDecorator next = new ChainedMenuFilterDecorator(menuFilter);
		
		// modify actual chain filter setting its next filter
		current.setNextFilter(next);
		
		// move forward to the new next filter so
		current = next;
		
		return this;
	}
	
	public MenuFilter build () {
		// filter closure: make sure no null filter is left in the chain
		current.setNextFilter(new DummyMenuFilter());
		
		return first;
	}
}
