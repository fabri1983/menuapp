package org.fabri1983.menuapp.protocol.menu;

import java.math.BigDecimal;

import javax.validation.constraints.Min;

import org.fabri1983.menuapp.core.menu.CurrencyType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.validation.OneOf;

public class MenuGroupingView {

	private BigDecimal priceFrom;
	private BigDecimal priceTo;
	private CurrencyType currency;

	@JsonCreator
	public MenuGroupingView(
			@JsonProperty("priceFrom") @Min(0) BigDecimal priceFrom,
			@JsonProperty("priceTo") @Min(0) BigDecimal priceTo,
			@JsonProperty("currency") @OneOf(value = {"DEFAULT_USD", "USD", "ARG_PESO", "YEN", "REAL"}, ignoreCase = false, message = "currency type is not valid")
			CurrencyType currency)
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
