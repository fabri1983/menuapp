package org.fabri1983.menuapp.api.config.hasfeature.impl;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.fabri1983.menuapp.core.entity.menu.CurrencyType;

public class MenuQueryConfig {

	@NotNull
	private CurrencyType defaultCurrency;
	@Min(1)
	private int pageLimit;
	@Min(1)
	private int maxAllowedResults;
	
	public CurrencyType getDefaultCurrency () {
		return defaultCurrency;
	}
	
	public int getPageLimit () {
		return pageLimit;
	}
	
	public int getMaxAllowedResults () {
		return maxAllowedResults;
	}
}
