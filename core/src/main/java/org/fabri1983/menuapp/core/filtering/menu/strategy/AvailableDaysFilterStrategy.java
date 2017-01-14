package org.fabri1983.menuapp.core.filtering.menu.strategy;

import java.util.List;

import org.fabri1983.menuapp.core.filtering.menu.MenuFilterable;
import org.fabri1983.menuapp.core.filtering.menu.visitor.AvailableDaysMenuFilterVisitor;

public class AvailableDaysFilterStrategy implements MenuFilterStrategy {

	private AvailableDaysMenuFilterVisitor filterVisitor;
	private List<String> availableDays;
	
	public AvailableDaysFilterStrategy(List<String> availableDays) {
		this.availableDays = availableDays;
		filterVisitor = new AvailableDaysMenuFilterVisitor(this);
	}
	
	@Override
	public boolean filter(MenuFilterable menu) {
		return menu.applyFilter(filterVisitor);
	}

	public List<String> getAvailableDays() {
		return availableDays;
	}
	
}
