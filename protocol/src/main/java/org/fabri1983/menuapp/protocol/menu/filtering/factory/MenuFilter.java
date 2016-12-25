package org.fabri1983.menuapp.protocol.menu.filtering.factory;

import org.fabri1983.menuapp.core.filtering.menu.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.protocol.menu.filtering.MenuFiltersView;

public interface MenuFilter {
	
	boolean isValid(MenuFiltersView filterData);
	
	void chain(ChainedMenuFilterBuilder filterChainBuilder, MenuFiltersView filterData);
}