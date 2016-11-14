package org.fabri1983.menuapp.core.filtering.visitor;

import java.time.temporal.ChronoUnit;

import org.fabri1983.menuapp.core.filtering.AvailableHoursMenuFilter;
import org.fabri1983.menuapp.core.menu.DefaultMenu;
import org.fabri1983.menuapp.core.menu.TimeConstraintMenu;

public class AvailableHoursFilterVisitor implements FilterVisitor {

	private AvailableHoursMenuFilter filter;
	
	public AvailableHoursFilterVisitor (AvailableHoursMenuFilter filter) {
		this.filter = filter;
	}
	
	@Override
	public boolean canFilterMenu(DefaultMenu menu) {
		return false;
	}

	@Override
	public boolean filter(TimeConstraintMenu menu) {
		return ChronoUnit.HOURS.between(filter.getHourFrom(), menu.getHourFrom()) >= 0L
				&& ChronoUnit.HOURS.between(filter.getHourTo(), menu.getHourTo()) <= 0L;
	}

}
