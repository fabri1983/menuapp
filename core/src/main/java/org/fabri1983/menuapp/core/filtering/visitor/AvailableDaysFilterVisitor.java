package org.fabri1983.menuapp.core.filtering.visitor;

import org.fabri1983.menuapp.core.filtering.AvailableDaysMenuFilter;
import org.fabri1983.menuapp.core.menu.DefaultMenu;
import org.fabri1983.menuapp.core.menu.TimeConstraintMenu;

public class AvailableDaysFilterVisitor implements FilterVisitor {

	private AvailableDaysMenuFilter filter;
	
	public AvailableDaysFilterVisitor (AvailableDaysMenuFilter filter) {
		this.filter = filter;
	}
	
	@Override
	public boolean canFilterMenu(DefaultMenu menu) {
		return false;
	}

	@Override
	public boolean filter(TimeConstraintMenu menu) {
		return menu.getAvailableDays().containsAll(filter.getAvailableDays());
	}

}
