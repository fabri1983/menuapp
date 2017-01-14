package org.fabri1983.menuapp.core.filtering.menu.visitor;

import java.time.temporal.ChronoUnit;

import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.core.filtering.menu.strategy.AvailableHoursFilterStrategy;

public class AvailableHoursFilterVisitor implements FilterVisitor {

	private AvailableHoursFilterStrategy filter;
	
	public AvailableHoursFilterVisitor(AvailableHoursFilterStrategy filter) {
		this.filter = filter;
	}
	
	@Override
	public boolean filter(DefaultMenu menu) {
		return false;
	}

	@Override
	public boolean filter(TimeConstraintMenu menu) {
		return ChronoUnit.HOURS.between(filter.getHourFrom(), menu.getHourFrom()) >= 0L
				&& ChronoUnit.HOURS.between(filter.getHourTo(), menu.getHourTo()) <= 0L;
	}

}
