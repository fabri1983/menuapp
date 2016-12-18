package org.fabri1983.menuapp.protocol.menu.filtering.factory;

import java.util.Arrays;
import java.util.List;

import org.fabri1983.menuapp.core.filtering.menu.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.core.filtering.menu.strategy.MenuFilterStrategy;
import org.fabri1983.menuapp.protocol.menu.filtering.MenuFiltersView;

public class MenuFiltersFactory {

	static final List<MenuFilter> filters = Arrays.asList(
										new MaxPriceFilter(),
										new AvailableHoursFilter(),
										new AvailableDaysFilter(),
										new DatesRangeFilter()
									);
	
	public static MenuFilterStrategy createFrom (MenuFiltersView filterData) {
		MenuFilterStrategy filterChain = setupFilterChain(filterData);
		return filterChain;
	}

	private static MenuFilterStrategy setupFilterChain (MenuFiltersView filterData) {
		ChainedMenuFilterBuilder filterChainBuilder = ChainedMenuFilterBuilder.newOne();
		
		filters.stream().filter(f -> f.isValid(filterData)).forEach( f -> f.chain(filterChainBuilder, filterData));
		
		MenuFilterStrategy filterChain = filterChainBuilder.build();
		return filterChain;
	}
	
}
