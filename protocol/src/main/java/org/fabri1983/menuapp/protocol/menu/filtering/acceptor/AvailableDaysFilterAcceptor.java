package org.fabri1983.menuapp.protocol.menu.filtering.acceptor;

import org.fabri1983.menuapp.core.filtering.menu.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.core.filtering.menu.strategy.AvailableDaysFilterStrategy;
import org.fabri1983.menuapp.protocol.menu.filtering.MenuFiltersView;

public class AvailableDaysFilterAcceptor implements MenuFilterAcceptor {

	@Override
	public boolean isValid(MenuFiltersView filterData) {
		return filterData.getAvailableDays() != null && !filterData.getAvailableDays().isEmpty();
	}

	@Override
	public void chain(ChainedMenuFilterBuilder filterChainBuilder, MenuFiltersView filterData) {
		filterChainBuilder.chain(new AvailableDaysFilterStrategy(filterData.getAvailableDays()));
	}
}