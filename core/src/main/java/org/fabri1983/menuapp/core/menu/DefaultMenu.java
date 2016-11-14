package org.fabri1983.menuapp.core.menu;

import java.math.BigDecimal;
import java.net.URL;

import org.fabri1983.menuapp.core.filtering.visitor.FilterVisitor;
import org.fabri1983.menuapp.core.presentation.converter.MenuPresentationConverter;

public class DefaultMenu implements Menu {

	private long id;
	private String name;
	private String description;
	private URL pictureUrl;
	private BigDecimal price;
	private CurrencyType currency;
	private int rating;
	
	public DefaultMenu(long id, String name, String description, URL pictureUrl, BigDecimal price,
			CurrencyType currency, int rating) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.pictureUrl = pictureUrl;
		this.price = price;
		this.currency = currency;
		this.rating = rating;
	}

	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public BigDecimal getPrice() {
		return price;
	}
	
	@Override
	public CurrencyType getCurrency() {
		return currency;
	}
	
	@Override
	public URL getPictureUrl() {
		return pictureUrl;
	}
	
	@Override
	public int getRating() {
		return rating;
	}
	
	@Override
	public void updateRating(int rating) {
		// FIXME move this to a class responsible for that job in case the rating process needs some extra calculations 
		this.rating = rating;
	}
	
	@Override
	public boolean applyFilter (FilterVisitor filterVisitor) {
		return filterVisitor.canFilterMenu(this);
	}
	
	@Override
	public boolean acceptsPresentationConverter (MenuPresentationConverter converter) {
		return converter.acceptsMenu(this);
	}
}
