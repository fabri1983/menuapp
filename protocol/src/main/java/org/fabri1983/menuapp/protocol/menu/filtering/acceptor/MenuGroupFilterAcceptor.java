package org.fabri1983.menuapp.protocol.menu.filtering.acceptor;

import org.fabri1983.menuapp.core.filtering.menu.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.protocol.menu.filtering.MenuGroupView;

public interface MenuGroupFilterAcceptor {
	
	boolean isValid(MenuGroupView groupData);
	
	void chain(ChainedMenuFilterBuilder filterChainBuilder, MenuGroupView groupData);
}