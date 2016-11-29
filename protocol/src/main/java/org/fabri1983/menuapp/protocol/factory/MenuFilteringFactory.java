package org.fabri1983.menuapp.protocol.factory;

import java.math.BigDecimal;

import org.fabri1983.menuapp.core.filtering.menu.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.core.filtering.menu.strategy.AvailableDaysFilterStrategy;
import org.fabri1983.menuapp.core.filtering.menu.strategy.AvailableHoursFilterStrategy;
import org.fabri1983.menuapp.core.filtering.menu.strategy.DatesRangeFilterStrategy;
import org.fabri1983.menuapp.core.filtering.menu.strategy.MenuFilterStrategy;
import org.fabri1983.menuapp.core.filtering.menu.strategy.PriceRangeFilterStrategy;
import org.fabri1983.menuapp.protocol.filtering.MenuFilteredView;
import org.fabri1983.menuapp.protocol.menu.MenuFiltersView;

public class MenuFilteringFactory {

	public static MenuFilterStrategy createFrom(MenuFilteredView menuFilteredRequest) {
		MenuFilterStrategy filterChain = setupFilterChain(menuFilteredRequest);
		return filterChain;
	}

	private static MenuFilterStrategy setupFilterChain (MenuFilteredView menuFilteredRequest) {
		ChainedMenuFilterBuilder filterChainBuilder = ChainedMenuFilterBuilder.newOne();
		MenuFiltersView filterData = menuFilteredRequest.getFilterData();
		
		if (filterData.hasMaxPriceFilter())
			addPriceFiltering(filterChainBuilder, filterData);
		if (filterData.hasAvailableHoursFilter())
			addHoursFiltering(filterChainBuilder, filterData);
		if (filterData.hasAvailableDaysFilter())
			addDaysFiltering(filterChainBuilder, filterData);
		if (filterData.hasAvailableDateFilter())
			addDateFiltering(filterChainBuilder, filterData);
		
		MenuFilterStrategy filterChain = filterChainBuilder.build();
		return filterChain;
	}
	
	private static void addPriceFiltering (ChainedMenuFilterBuilder filterChainBuilder, MenuFiltersView filterData) {
		filterChainBuilder.chain(new PriceRangeFilterStrategy(BigDecimal.ZERO, filterData.getMaxPrice(), filterData.getCurrency()));
	}
	
	private static void addHoursFiltering (ChainedMenuFilterBuilder filterChainBuilder, MenuFiltersView filterData) {
		filterChainBuilder.chain(new AvailableHoursFilterStrategy(filterData.getHourFrom(), filterData.getHourTo()));
	}
	
	private static void addDaysFiltering (ChainedMenuFilterBuilder filterChainBuilder, MenuFiltersView filterData) {
		filterChainBuilder.chain(new AvailableDaysFilterStrategy(filterData.getAvailableDays()));
	}
	
	private static void addDateFiltering (ChainedMenuFilterBuilder filterChainBuilder, MenuFiltersView filterData) {
		filterChainBuilder.chain(new DatesRangeFilterStrategy(filterData.getAvailableDateFrom(), filterData.getAvailableDateTo()));
	}
	
}
