package org.fabri1983.menuapp.core.filtering.menu.strategy;

import org.fabri1983.menuapp.core.entity.menu.CurrencyType;
import org.fabri1983.menuapp.core.filtering.menu.MenuFilterable;
import org.fabri1983.menuapp.core.filtering.menu.visitor.RatingRangeFilterVisitor;

public class RatingRangeFilterStrategy implements MenuFilterStrategy {

	private RatingRangeFilterVisitor filterVisitor;
	private int ratingFrom;
	private int ratingTo;
	
	public RatingRangeFilterStrategy(int ratingFrom, int ratingTo, CurrencyType currency) {
		this.ratingFrom = ratingFrom;
		this.ratingTo = ratingTo;
		filterVisitor = new RatingRangeFilterVisitor(this);
	}

	@Override
	public boolean filter(MenuFilterable menu) {
		return menu.applyFilter(filterVisitor);
	}

	public int getRatingFrom() {
		return ratingFrom;
	}

	public int getRatingTo() {
		return ratingTo;
	}
	
}
