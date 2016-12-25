package org.fabri1983.menuapp.protocol.menu.filtering.factory;

import java.math.BigDecimal;

import org.fabri1983.menuapp.core.filtering.menu.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.core.filtering.menu.strategy.PriceRangeFilterStrategy;
import org.fabri1983.menuapp.protocol.menu.filtering.MenuFiltersView;

public class MaxPriceFilter implements MenuFilter {

	@Override
	public boolean isValid(MenuFiltersView filterData) {
		return filterData.getMaxPrice() != null;
	}

	@Override
	public void chain(ChainedMenuFilterBuilder filterChainBuilder, MenuFiltersView filterData) {
		filterChainBuilder.chain(new PriceRangeFilterStrategy(BigDecimal.ZERO, filterData.getMaxPrice(), filterData.getCurrency()));
	}
}