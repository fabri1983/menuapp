package org.fabri1983.menuapp.core.menu;

import java.math.BigDecimal;
import java.net.URL;

import org.fabri1983.menuapp.core.filtering.visitor.FilterVisitor;
import org.fabri1983.menuapp.core.presentation.converter.MenuPresentationConverter;

public interface Menu {

	long getId();
	String getName();
	String getDescription();
	BigDecimal getPrice();
	CurrencyType getCurrency();
	URL getPictureUrl();
	int getRating();
	void updateRating(int rating);
	
	boolean applyFilter (FilterVisitor filterVisitor);
	boolean acceptsPresentationConverter (MenuPresentationConverter converter);
}
