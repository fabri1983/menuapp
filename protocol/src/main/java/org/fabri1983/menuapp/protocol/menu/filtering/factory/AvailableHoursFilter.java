package org.fabri1983.menuapp.protocol.menu.filtering.factory;

import org.fabri1983.menuapp.core.filtering.menu.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.core.filtering.menu.strategy.AvailableHoursFilterStrategy;
import org.fabri1983.menuapp.protocol.menu.filtering.MenuFiltersView;

public class AvailableHoursFilter implements MenuFilter {

	@Override
	public boolean isValid(MenuFiltersView filterData) {
		return filterData.getHourFrom() != null && filterData.getHourTo() != null;
	}

	@Override
	public void chain(ChainedMenuFilterBuilder filterChainBuilder, MenuFiltersView filterData) {
		filterChainBuilder.chain(new AvailableHoursFilterStrategy(filterData.getHourFrom(), filterData.getHourTo()));
	}
}