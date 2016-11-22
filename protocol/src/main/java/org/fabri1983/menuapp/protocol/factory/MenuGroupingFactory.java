package org.fabri1983.menuapp.protocol.factory;

import org.fabri1983.menuapp.core.filtering.MenuFilter;
import org.fabri1983.menuapp.core.filtering.PriceRangeMenuFilter;
import org.fabri1983.menuapp.core.filtering.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.protocol.grouping.MenuGroupView;
import org.fabri1983.menuapp.protocol.menu.MenuGroupingView;

public class MenuGroupingFactory {

	public static MenuFilter createFrom(MenuGroupView menuGroupRequest) {
		MenuFilter filterChain = setupFilterChain(menuGroupRequest);
		return filterChain;
	}

	private static MenuFilter setupFilterChain (MenuGroupView menuGroupRequest) {
		ChainedMenuFilterBuilder filterChainBuilder = ChainedMenuFilterBuilder.newOne();
		MenuGroupingView groupData = menuGroupRequest.getGroupData();
		
		if (groupData.hasPriceFilter())
			addRangePriceFiltering(filterChainBuilder, groupData);
//		if (groupData.hasRatingFilter())
//			addRatingRangeFiltering(filterChainBuilder, groups);
		
		MenuFilter filterChain = filterChainBuilder.build();
		return filterChain;
	}

	private static void addRangePriceFiltering (ChainedMenuFilterBuilder filterChainBuilder, MenuGroupingView groupData) {
		filterChainBuilder.chain(new PriceRangeMenuFilter(groupData.getPriceFrom(), groupData.getPriceTo(), groupData.getCurrency()));
	}
	
//	private static void addRatingRangeFiltering (ChainedMenuFilterBuilder filterChainBuilder, MenuGroupingView groupData) {
//		filterChainBuilder.chain(new RatingRangeMenuFilter(groupData.getRatingFrom(), groupData.getRatingTo()));
//	}
	
}
