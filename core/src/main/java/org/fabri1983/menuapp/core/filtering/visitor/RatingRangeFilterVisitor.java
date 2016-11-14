package org.fabri1983.menuapp.core.filtering.visitor;

import org.fabri1983.menuapp.core.filtering.RatingRangeMenuFilter;
import org.fabri1983.menuapp.core.menu.DefaultMenu;
import org.fabri1983.menuapp.core.menu.TimeConstraintMenu;

public class RatingRangeFilterVisitor implements FilterVisitor {

	private RatingRangeMenuFilter filter;
	
	public RatingRangeFilterVisitor (RatingRangeMenuFilter filter) {
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
