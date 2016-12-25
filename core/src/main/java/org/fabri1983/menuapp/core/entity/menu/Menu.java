package org.fabri1983.menuapp.core.entity.menu;

import java.math.BigDecimal;
import java.net.URL;

import org.fabri1983.menuapp.core.converter.menu.MenuTypeConverterRequester;
import org.fabri1983.menuapp.core.filtering.menu.visitor.FilterVisitor;

public interface Menu {

	long getId();
	String getName();
	String getDescription();
	BigDecimal getPrice();
	CurrencyType getCurrency();
	URL getPictureUrl();
	int getRating();
	void updateRating(int rating);
	
	boolean applyFilter(FilterVisitor filterVisitor);
	boolean acceptsConverter(MenuTypeConverterRequester converter);
}
