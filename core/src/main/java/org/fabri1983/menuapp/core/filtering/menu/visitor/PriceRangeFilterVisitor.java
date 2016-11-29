package org.fabri1983.menuapp.core.filtering.menu.visitor;

import java.math.BigDecimal;

import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.core.filtering.menu.strategy.PriceRangeFilterStrategy;

public class PriceRangeFilterVisitor implements FilterVisitor {

	private PriceRangeFilterStrategy filter;
	
	public PriceRangeFilterVisitor (PriceRangeFilterStrategy filter) {
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
