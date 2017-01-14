package org.fabri1983.menuapp.core.filtering.menu.strategy;

import java.time.LocalDateTime;

import org.fabri1983.menuapp.core.filtering.menu.MenuFilterable;
import org.fabri1983.menuapp.core.filtering.menu.visitor.DatesRangeFilterVisitor;

public class DatesRangeFilterStrategy implements MenuFilterStrategy {

	private DatesRangeFilterVisitor filterVisitor;
	private LocalDateTime dateFrom;
	private LocalDateTime dateTo;
	
	public DatesRangeFilterStrategy(LocalDateTime dateFrom, LocalDateTime dateTo) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		filterVisitor = new DatesRangeFilterVisitor(this);
	}
	
	@Override
	public boolean filter(MenuFilterable menu) {
		return menu.applyFilter(filterVisitor);
	}

	public LocalDateTime getDateFrom() {
		return dateFrom;
	}

	public LocalDateTime getDateTo() {
		return dateTo;
	}
	
}
