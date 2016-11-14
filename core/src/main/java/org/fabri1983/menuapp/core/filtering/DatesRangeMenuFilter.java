package org.fabri1983.menuapp.core.filtering;

import java.time.LocalDateTime;

import org.fabri1983.menuapp.core.filtering.visitor.DatesRangeFilterVisitor;
import org.fabri1983.menuapp.core.menu.Menu;

public class DatesRangeMenuFilter implements MenuFilter {

	private DatesRangeFilterVisitor filterVisitor;
	private LocalDateTime dateFrom;
	private LocalDateTime dateTo;
	
	public DatesRangeMenuFilter (LocalDateTime dateFrom, LocalDateTime dateTo) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		filterVisitor = new DatesRangeFilterVisitor(this);
	}
	
	@Override
	public boolean accepts(Menu menu) {
		return menu.applyFilter(filterVisitor);
	}

	public LocalDateTime getDateFrom() {
		return dateFrom;
	}

	public LocalDateTime getDateTo() {
		return dateTo;
	}
	
}
