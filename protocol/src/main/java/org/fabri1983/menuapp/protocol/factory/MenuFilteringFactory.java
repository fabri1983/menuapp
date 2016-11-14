package org.fabri1983.menuapp.protocol.factory;

import java.math.BigDecimal;

import org.fabri1983.menuapp.core.filtering.AvailableDaysMenuFilter;
import org.fabri1983.menuapp.core.filtering.AvailableHoursMenuFilter;
import org.fabri1983.menuapp.core.filtering.DatesRangeMenuFilter;
import org.fabri1983.menuapp.core.filtering.MenuFilter;
import org.fabri1983.menuapp.core.filtering.PriceRangeMenuFilter;
import org.fabri1983.menuapp.core.filtering.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.protocol.core.MenuFiltersPresentation;
import org.fabri1983.menuapp.protocol.filtering.MenuFilteredRequest;

public class MenuFilteringFactory {

	public static MenuFilter createFrom(MenuFilteredRequest menuFilteredRequest) {
		MenuFilter filterChain = setupFilterChain(menuFilteredRequest);
		return filterChain;
	}

	private static MenuFilter setupFilterChain (MenuFilteredRequest menuFilteredRequest) {
		ChainedMenuFilterBuilder filterChainBuilder = ChainedMenuFilterBuilder.newOne();
		MenuFiltersPresentation filterData = menuFilteredRequest.getFilterData();
		
		if (filterData.hasPriceFilter())
			addPriceFiltering(filterChainBuilder, filterData);
		if (filterData.hasAvailableHoursFilter())
			addHoursFiltering(filterChainBuilder, filterData);
		if (filterData.hasAvailableDaysFilter())
			addDaysFiltering(filterChainBuilder, filterData);
		if (filterData.hasAvailableDateFilter())
			addDateFiltering(filterChainBuilder, filterData);
		
		MenuFilter filterChain = filterChainBuilder.build();
		return filterChain;
	}
	
	private static void addPriceFiltering (ChainedMenuFilterBuilder filterChainBuilder, MenuFiltersPresentation filterData) {
		filterChainBuilder.chain(new PriceRangeMenuFilter(BigDecimal.ZERO, filterData.getMaxPrice(), filterData.getCurrency()));
	}
	
	private static void addHoursFiltering (ChainedMenuFilterBuilder filterChainBuilder, MenuFiltersPresentation filterData) {
		filterChainBuilder.chain(new AvailableHoursMenuFilter(filterData.getHourFrom(), filterData.getHourTo()));
	}
	
	private static void addDaysFiltering (ChainedMenuFilterBuilder filterChainBuilder, MenuFiltersPresentation filterData) {
		filterChainBuilder.chain(new AvailableDaysMenuFilter(filterData.getAvailableDays()));
	}
	
	private static void addDateFiltering (ChainedMenuFilterBuilder filterChainBuilder, MenuFiltersPresentation filterData) {
		filterChainBuilder.chain(new DatesRangeMenuFilter(filterData.getAvailableDateFrom(), filterData.getAvailableDateTo()));
	}
	
}
