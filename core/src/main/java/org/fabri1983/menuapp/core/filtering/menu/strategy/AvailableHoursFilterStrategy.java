package org.fabri1983.menuapp.core.filtering.menu.strategy;

import java.time.LocalTime;

import org.fabri1983.menuapp.core.filtering.menu.MenuFilterable;
import org.fabri1983.menuapp.core.filtering.menu.visitor.AvailableHoursFilterVisitor;

public class AvailableHoursFilterStrategy implements MenuFilterStrategy {

	private AvailableHoursFilterVisitor filterVisitor;
	private LocalTime hourFrom;
	private LocalTime hourTo;
	
	public AvailableHoursFilterStrategy(LocalTime hourFrom, LocalTime hourTo) {
		this.hourFrom = hourFrom;
		this.hourTo = hourTo;
		filterVisitor = new AvailableHoursFilterVisitor(this);
	}
	
	@Override
	public boolean filter(MenuFilterable menu) {
		return menu.applyFilter(filterVisitor);
	}

	public LocalTime getHourFrom() {
		return hourFrom;
	}

	public LocalTime getHourTo() {
		return hourTo;
	}
	
}
