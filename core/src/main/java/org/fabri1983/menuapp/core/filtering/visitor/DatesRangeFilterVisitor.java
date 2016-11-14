package org.fabri1983.menuapp.core.filtering.visitor;

import org.fabri1983.menuapp.core.filtering.DatesRangeMenuFilter;
import org.fabri1983.menuapp.core.menu.DefaultMenu;
import org.fabri1983.menuapp.core.menu.TimeConstraintMenu;

public class DatesRangeFilterVisitor implements FilterVisitor {

	private DatesRangeMenuFilter filter;
	
	public DatesRangeFilterVisitor (DatesRangeMenuFilter filter) {
		this.filter = filter;
	}
	
	@Override
	public boolean canFilterMenu(DefaultMenu menu) {
		return false;
	}

	@Override
	public boolean filter(TimeConstraintMenu menu) {
		return menu.getAvailableDateFrom().compareTo(filter.getDateFrom()) >= 0
				&& menu.getAvailableDateTo().compareTo(filter.getDateTo()) <= 0;
	}

}
