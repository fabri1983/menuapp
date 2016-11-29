package org.fabri1983.menuapp.core.filtering.menu.visitor;

import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.core.filtering.menu.strategy.RatingRangeFilterStrategy;

public class RatingRangeFilterVisitor implements FilterVisitor {

	private RatingRangeFilterStrategy filter;
	
	public RatingRangeFilterVisitor (RatingRangeFilterStrategy filter) {
		this.filter = filter;
	}
	
	@Override
	public boolean canFilterMenu(DefaultMenu menu) {
		return isInRange(menu.getRating());
	}

	@Override
	public boolean filter(TimeConstraintMenu menu) {
		return isInRange(menu.getRating());
	}
	
	private boolean isInRange(int menuRating) {
		return menuRating >= filter.getRatingFrom() && menuRating <= filter.getRatingTo();
	}

}
