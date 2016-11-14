package org.fabri1983.menuapp.core.filtering;

import java.time.LocalTime;

import org.fabri1983.menuapp.core.filtering.visitor.AvailableHoursFilterVisitor;
import org.fabri1983.menuapp.core.menu.Menu;

public class AvailableHoursMenuFilter implements MenuFilter {

	private AvailableHoursFilterVisitor filterVisitor;
	private LocalTime hourFrom;
	private LocalTime hourTo;
	
	public AvailableHoursMenuFilter (LocalTime hourFrom, LocalTime hourTo) {
		this.hourFrom = hourFrom;
		this.hourTo = hourTo;
		filterVisitor = new AvailableHoursFilterVisitor(this);
	}
	
	@Override
	public boolean accepts(Menu menu) {
		return menu.applyFilter(filterVisitor);
	}

	public LocalTime getHourFrom() {
		return hourFrom;
	}

	public LocalTime getHourTo() {
		return hourTo;
	}
	
}
