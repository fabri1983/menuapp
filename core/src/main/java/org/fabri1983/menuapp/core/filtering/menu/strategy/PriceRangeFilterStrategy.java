package org.fabri1983.menuapp.core.filtering.menu.strategy;

import java.math.BigDecimal;

import org.fabri1983.menuapp.core.entity.menu.CurrencyType;
import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.core.filtering.menu.visitor.PriceRangeFilterVisitor;

public class PriceRangeFilterStrategy implements MenuFilterStrategy {

	private PriceRangeFilterVisitor filterVisitor;
	private BigDecimal priceFrom;
	private BigDecimal priceTo;
	private CurrencyType currency;
	
	public PriceRangeFilterStrategy (BigDecimal priceFrom, BigDecimal priceTo, CurrencyType currency) {
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
		this.currency = currency;
		filterVisitor = new PriceRangeFilterVisitor(this);
	}

	@Override
	public boolean accepts(Menu menu) {
		return menu.applyFilter(filterVisitor);
	}
	
	public BigDecimal getPriceFrom() {
		return priceFrom;
	}
	
	public BigDecimal getPriceTo() {
		return priceTo;
	}
	
	public CurrencyType getCurrency() {
		return currency;
	}
	
}
