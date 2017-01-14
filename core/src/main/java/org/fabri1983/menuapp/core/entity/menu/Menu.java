package org.fabri1983.menuapp.core.entity.menu;

import java.math.BigDecimal;
import java.net.URL;

import org.fabri1983.menuapp.core.converter.menu.MenuConvertable;
import org.fabri1983.menuapp.core.filtering.menu.MenuFilterable;

public interface Menu extends MenuFilterable, MenuConvertable {

	long getId();
	
	String getName();
	
	String getDescription();
	
	BigDecimal getPrice();
	
	CurrencyType getCurrency();
	
	URL getPictureUrl();
	
	int getRating();
	
	void updateRating(int rating);
	
}
