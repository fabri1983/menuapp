package org.fabri1983.menuapp.protocol.menu.filtering.acceptor;

import org.fabri1983.menuapp.core.filtering.menu.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.protocol.menu.filtering.MenuFiltersView;

public interface MenuFilterAcceptor {
	
	boolean isValid(MenuFiltersView filterData);
	
	void chain(ChainedMenuFilterBuilder filterChainBuilder, MenuFiltersView filterData);
}