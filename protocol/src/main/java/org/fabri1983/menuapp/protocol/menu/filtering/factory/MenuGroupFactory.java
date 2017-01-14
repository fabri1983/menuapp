package org.fabri1983.menuapp.protocol.menu.filtering.factory;

import java.util.Arrays;
import java.util.List;

import org.fabri1983.menuapp.core.filtering.menu.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.core.filtering.menu.strategy.MenuFilterStrategy;
import org.fabri1983.menuapp.protocol.menu.filtering.MenuGroupView;
import org.fabri1983.menuapp.protocol.menu.filtering.acceptor.MenuGroupFilterAcceptor;
import org.fabri1983.menuapp.protocol.menu.filtering.acceptor.RangePriceFilter;

public class MenuGroupFactory {

	static final List<MenuGroupFilterAcceptor> filters = Arrays.asList(
			new RangePriceFilter()
		);
	
	public static MenuFilterStrategy createFrom(MenuGroupView groupData) {
		MenuFilterStrategy filterChain = setupFilterChain(groupData);
		return filterChain;
	}

	private static MenuFilterStrategy setupFilterChain(MenuGroupView groupData) {
		ChainedMenuFilterBuilder filterChainBuilder = ChainedMenuFilterBuilder.newOne();
		
		filters.stream().filter(f -> f.isValid(groupData)).forEach( f -> f.chain(filterChainBuilder, groupData));
		
		MenuFilterStrategy filterChain = filterChainBuilder.build();
		return filterChain;
	}
	
}
