package org.fabri1983.menuapp.core.filtering.menu.visitor;

import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.core.filtering.menu.strategy.AvailableDaysFilterStrategy;

public class AvailableDaysMenuFilterVisitor implements FilterVisitor {

	private AvailableDaysFilterStrategy filter;
	
	public AvailableDaysMenuFilterVisitor(AvailableDaysFilterStrategy filter) {
		this.filter = filter;
	}
	
	@Override
	public boolean filter(DefaultMenu menu) {
		return false;
	}

	@Override
	public boolean filter(TimeConstraintMenu menu) {
		return menu.getAvailableDays().containsAll(filter.getAvailableDays());
	}

}
