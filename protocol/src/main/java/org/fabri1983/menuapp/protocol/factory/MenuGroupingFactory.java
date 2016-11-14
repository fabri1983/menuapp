package org.fabri1983.menuapp.protocol.factory;

import org.fabri1983.menuapp.core.filtering.MenuFilter;
import org.fabri1983.menuapp.core.filtering.PriceRangeMenuFilter;
import org.fabri1983.menuapp.core.filtering.decorator.ChainedMenuFilterBuilder;
import org.fabri1983.menuapp.protocol.core.MenuGroupingPresentation;
import org.fabri1983.menuapp.protocol.grouping.MenuGroupRequest;

public class MenuGroupingFactory {

	public static MenuFilter createFrom(MenuGroupRequest menuGroupRequest) {
		MenuFilter filterChain = setupFilterChain(menuGroupRequest);
		return filterChain;
	}

	private static MenuFilter setupFilterChain (MenuGroupRequest menuGroupRequest) {
		ChainedMenuFilterBuilder filterChainBuilder = ChainedMenuFilterBuilder.newOne();
		MenuGroupingPresentation groupData = menuGroupRequest.getGroupData();
		
		if (groupData.hasPriceFilter())
			addRangePriceFiltering(filterChainBuilder, groupData);
//		if (groups.hasRatingFilter())
//			addRatingRangeFiltering(filterChainBuilder, groups);
		
		MenuFilter filterChain = filterChainBuilder.build();
		return filterChain;
	}

	private static void addRangePriceFiltering (ChainedMenuFilterBuilder filterChainBuilder, MenuGroupingPresentation groupData) {
		filterChainBuilder.chain(new PriceRangeMenuFilter(groupData.getPriceFrom(), groupData.getPriceTo(), groupData.getCurrency()));
	}
	
//	private static void addRatingRangeFiltering (ChainedMenuFilterBuilder filterChainBuilder, MenuGroupingPresentation groupData) {
//		filterChainBuilder.chain(new RatingRangeMenuFilter(groupData.getRatingFrom(), groupData.getRatingTo()));
//	}
	
}
