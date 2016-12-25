package org.fabri1983.menuapp.protocol.menu.filtering.factory;

import org.fabri1983.menuapp.core.filtering.menu.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.protocol.menu.filtering.MenuGroupView;

public interface MenuGroupFilter {
	
	boolean isValid(MenuGroupView groupData);
	
	void chain(ChainedMenuFilterBuilder filterChainBuilder, MenuGroupView groupData);
}