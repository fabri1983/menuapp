package org.fabri1983.menuapp.protocol.menu.filtering.factory;

import java.util.Arrays;
import java.util.List;

import org.fabri1983.menuapp.core.filtering.menu.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.core.filtering.menu.strategy.MenuFilterStrategy;
import org.fabri1983.menuapp.protocol.menu.filtering.MenuFiltersView;
import org.fabri1983.menuapp.protocol.menu.filtering.acceptor.AvailableDaysFilterAcceptor;
import org.fabri1983.menuapp.protocol.menu.filtering.acceptor.AvailableHoursFilterAcceptor;
import org.fabri1983.menuapp.protocol.menu.filtering.acceptor.DatesRangeFilterAcceptor;
import org.fabri1983.menuapp.protocol.menu.filtering.acceptor.MaxPriceFilterAcceptor;
import org.fabri1983.menuapp.protocol.menu.filtering.acceptor.MenuFilterAcceptor;

public class MenuFiltersFactory {

	static final List<MenuFilterAcceptor> filters = Arrays.asList(
										new MaxPriceFilterAcceptor(),
										new AvailableHoursFilterAcceptor(),
										new AvailableDaysFilterAcceptor(),
										new DatesRangeFilterAcceptor()
									);
	
	public static MenuFilterStrategy createFrom(MenuFiltersView filterData) {
		MenuFilterStrategy filterChain = setupFilterChain(filterData);
		return filterChain;
	}

	private static MenuFilterStrategy setupFilterChain(MenuFiltersView filterData) {
		ChainedMenuFilterBuilder filterChainBuilder = ChainedMenuFilterBuilder.newOne();
		
		filters.stream().filter(f -> f.isValid(filterData)).forEach( f -> f.chain(filterChainBuilder, filterData));
		
		MenuFilterStrategy filterChain = filterChainBuilder.build();
		return filterChain;
	}
	
}
