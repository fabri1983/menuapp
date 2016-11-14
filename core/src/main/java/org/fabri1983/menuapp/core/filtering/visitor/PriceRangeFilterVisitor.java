package org.fabri1983.menuapp.core.filtering.visitor;

import java.math.BigDecimal;

import org.fabri1983.menuapp.core.filtering.PriceRangeMenuFilter;
import org.fabri1983.menuapp.core.menu.DefaultMenu;
import org.fabri1983.menuapp.core.menu.TimeConstraintMenu;

public class PriceRangeFilterVisitor implements FilterVisitor {

	private PriceRangeMenuFilter filter;
	
	public PriceRangeFilterVisitor (PriceRangeMenuFilter filter) {
		this.filter = filter;
	}
	
	@Override
	public boolean canFilterMenu(DefaultMenu menu) {
		// FIXME convert menu price to the filter currency type
		BigDecimal priceMenu = menu.getPrice();
		return isInRange(priceMenu);
	}

	@Override
	public boolean filter(TimeConstraintMenu menu) {
		// FIXME convert menu price to the filter currency type
		BigDecimal priceMenu = menu.getPrice();
		return isInRange(priceMenu);
	}
	
	private boolean isInRange(BigDecimal priceMenu) {
		return priceMenu.compareTo(filter.getPriceFrom()) >= 0
				&& priceMenu.compareTo(filter.getPriceTo()) <= 0;
	}

}
