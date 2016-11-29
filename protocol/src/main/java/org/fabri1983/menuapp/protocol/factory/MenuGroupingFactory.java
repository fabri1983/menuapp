package org.fabri1983.menuapp.protocol.factory;

import org.fabri1983.menuapp.core.filtering.menu.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.core.filtering.menu.strategy.MenuFilterStrategy;
import org.fabri1983.menuapp.core.filtering.menu.strategy.PriceRangeFilterStrategy;
import org.fabri1983.menuapp.protocol.grouping.MenuGroupView;
import org.fabri1983.menuapp.protocol.menu.MenuGroupingView;

public class MenuGroupingFactory {

	public static MenuFilterStrategy createFrom(MenuGroupView menuGroupRequest) {
		MenuFilterStrategy filterChain = setupFilterChain(menuGroupRequest);
		return filterChain;
	}

	private static MenuFilterStrategy setupFilterChain (MenuGroupView menuGroupRequest) {
		ChainedMenuFilterBuilder filterChainBuilder = ChainedMenuFilterBuilder.newOne();
		MenuGroupingView groupData = menuGroupRequest.getGroupData();
		
		if (groupData.hasPriceFilter())
			addRangePriceFiltering(filterChainBuilder, groupData);
//		if (groupData.hasRatingFilter())
//			addRatingRangeFiltering(filterChainBuilder, groups);
		
		MenuFilterStrategy filterChain = filterChainBuilder.build();
		return filterChain;
	}

	private static void addRangePriceFiltering (ChainedMenuFilterBuilder filterChainBuilder, MenuGroupingView groupData) {
		filterChainBuilder.chain(new PriceRangeFilterStrategy(groupData.getPriceFrom(), groupData.getPriceTo(), groupData.getCurrency()));
	}
	
//	private static void addRatingRangeFiltering (ChainedMenuFilterBuilder filterChainBuilder, MenuGroupingView groupData) {
//		filterChainBuilder.chain(new RatingRangeMenuFilter(groupData.getRatingFrom(), groupData.getRatingTo()));
//	}
	
}
