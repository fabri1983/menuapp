package org.fabri1983.menuapp.protocol.menu.filtering.acceptor;

import org.fabri1983.menuapp.core.filtering.menu.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.core.filtering.menu.strategy.DatesRangeFilterStrategy;
import org.fabri1983.menuapp.protocol.menu.filtering.MenuFiltersView;

public class DatesRangeFilterAcceptor implements MenuFilterAcceptor {

	@Override
	public boolean isValid(MenuFiltersView filterData) {
		return filterData.getAvailableDateFrom() != null && filterData.getAvailableDateTo() != null;
	}

	@Override
	public void chain(ChainedMenuFilterBuilder filterChainBuilder, MenuFiltersView filterData) {
		filterChainBuilder.chain(new DatesRangeFilterStrategy(filterData.getAvailableDateFrom(), filterData.getAvailableDateTo()));
	}
}