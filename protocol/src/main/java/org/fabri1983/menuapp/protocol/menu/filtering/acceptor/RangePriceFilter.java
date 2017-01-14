package org.fabri1983.menuapp.protocol.menu.filtering.acceptor;

import org.fabri1983.menuapp.core.filtering.menu.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.core.filtering.menu.strategy.PriceRangeFilterStrategy;
import org.fabri1983.menuapp.protocol.menu.filtering.MenuGroupView;

public class RangePriceFilter implements MenuGroupFilterAcceptor {

	@Override
	public boolean isValid(MenuGroupView groupData) {
		return groupData.getPriceFrom() != null && groupData.getPriceTo() != null;
	}

	@Override
	public void chain(ChainedMenuFilterBuilder filterChainBuilder, MenuGroupView groupData) {
		filterChainBuilder.chain(new PriceRangeFilterStrategy(groupData.getPriceFrom(), groupData.getPriceTo(), groupData.getCurrency()));
	}
}