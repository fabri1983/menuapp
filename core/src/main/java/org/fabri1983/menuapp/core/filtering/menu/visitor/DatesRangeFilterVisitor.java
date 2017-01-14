package org.fabri1983.menuapp.core.filtering.menu.visitor;

import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.core.filtering.menu.strategy.DatesRangeFilterStrategy;

public class DatesRangeFilterVisitor implements FilterVisitor {

	private DatesRangeFilterStrategy filter;
	
	public DatesRangeFilterVisitor(DatesRangeFilterStrategy filter) {
		this.filter = filter;
	}
	
	@Override
	public boolean filter(DefaultMenu menu) {
		return false;
	}

	@Override
	public boolean filter(TimeConstraintMenu menu) {
		return menu.getAvailableDateFrom().compareTo(filter.getDateFrom()) >= 0
				&& menu.getAvailableDateTo().compareTo(filter.getDateTo()) <= 0;
	}

}
