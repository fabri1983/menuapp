package org.fabri1983.menuapp.core.filtering;

import java.util.List;

import org.fabri1983.menuapp.core.filtering.visitor.AvailableDaysFilterVisitor;
import org.fabri1983.menuapp.core.menu.Menu;

public class AvailableDaysMenuFilter implements MenuFilter {

	private AvailableDaysFilterVisitor filterVisitor;
	private List<String> availableDays;
	
	public AvailableDaysMenuFilter (List<String> availableDays) {
		this.availableDays = availableDays;
		filterVisitor = new AvailableDaysFilterVisitor(this);
	}
	
	@Override
	public boolean accepts(Menu menu) {
		return menu.applyFilter(filterVisitor);
	}

	public List<String> getAvailableDays() {
		return availableDays;
	}
	
}
