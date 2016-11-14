package org.fabri1983.menuapp.core.filtering;

import org.fabri1983.menuapp.core.filtering.visitor.RatingRangeFilterVisitor;
import org.fabri1983.menuapp.core.menu.CurrencyType;
import org.fabri1983.menuapp.core.menu.Menu;

public class RatingRangeMenuFilter implements MenuFilter {

	private RatingRangeFilterVisitor filterVisitor;
	private int ratingFrom;
	private int ratingTo;
	
	public RatingRangeMenuFilter (int ratingFrom, int ratingTo, CurrencyType currency) {
		this.ratingFrom = ratingFrom;
		this.ratingTo = ratingTo;
		filterVisitor = new RatingRangeFilterVisitor(this);
	}

	@Override
	public boolean accepts(Menu menu) {
		return menu.applyFilter(filterVisitor);
	}

	public int getRatingFrom() {
		return ratingFrom;
	}

	public int getRatingTo() {
		return ratingTo;
	}
	
}
