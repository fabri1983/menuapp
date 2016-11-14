package org.fabri1983.menuapp.core.filtering;

import java.math.BigDecimal;

import org.fabri1983.menuapp.core.filtering.visitor.PriceRangeFilterVisitor;
import org.fabri1983.menuapp.core.menu.CurrencyType;
import org.fabri1983.menuapp.core.menu.Menu;

public class PriceRangeMenuFilter implements MenuFilter {

	private PriceRangeFilterVisitor filterVisitor;
	private BigDecimal priceFrom;
	private BigDecimal priceTo;
	private CurrencyType currency;
	
	public PriceRangeMenuFilter (BigDecimal priceFrom, BigDecimal priceTo, CurrencyType currency) {
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
