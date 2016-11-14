package org.fabri1983.menuapp.protocol.core;

import java.math.BigDecimal;

import javax.validation.constraints.Min;

import org.fabri1983.menuapp.core.menu.CurrencyType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.validation.OneOf;

public class MenuGroupingPresentation {

	@Min(0)
	private BigDecimal priceFrom;
	@Min(0)
	private BigDecimal priceTo;
	@OneOf(value = {"DEFAULT_USD", "USD", "ARG_PESO", "YEN", "REAL"}, ignoreCase = false, message = "currency type is not valid")
	private CurrencyType currency;

	@JsonCreator
	public MenuGroupingPresentation(
			@JsonProperty("priceFrom") BigDecimal priceFrom,
			@JsonProperty("priceTo") BigDecimal priceTo,
			@JsonProperty("currency") CurrencyType currency)
	{	
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
		this.currency = currency;
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

	public boolean hasPriceFilter() {
		return priceFrom != null && priceTo != null;
	}

}
