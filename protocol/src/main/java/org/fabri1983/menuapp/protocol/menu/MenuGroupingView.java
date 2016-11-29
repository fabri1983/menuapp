package org.fabri1983.menuapp.protocol.menu;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.fabri1983.menuapp.core.entity.menu.CurrencyType;
import org.fabri1983.menuapp.protocol.validation.StringEnumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuGroupingView {

	@NotNull @Min(0)
	private BigDecimal priceFrom;
	@NotNull @Min(0)
	private BigDecimal priceTo;
	@NotNull @StringEnumeration(enumClass = CurrencyType.class)
	private String currency;
	
	@JsonCreator
	public MenuGroupingView(
			@JsonProperty("priceFrom") BigDecimal priceFrom,
			@JsonProperty("priceTo") BigDecimal priceTo,
			@JsonProperty("currency") String currency)
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
		return CurrencyType.valueOf(currency);
	}

	public boolean hasPriceFilter() {
		return priceFrom != null && priceTo != null;
	}

}
