package org.fabri1983.menuapp.protocol.menu.filtering;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.fabri1983.menuapp.core.entity.menu.CurrencyType;
import org.fabri1983.menuapp.protocol.validation.StringEnumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.annotations.ApiModel;

@ApiModel(value="MenuGroupView", description="Presentation of a menu grouping request")
@JsonNaming(LowerCaseStrategy.class)
public class MenuGroupView {

	@Min(1) @Max(50)
	private int maxResults;
	@NotNull @Min(0)
	private BigDecimal priceFrom;
	@NotNull @Min(0)
	private BigDecimal priceTo;
	@NotNull @StringEnumeration(enumClass = CurrencyType.class)
	private String currency;
	
	@JsonCreator
	public MenuGroupView(
			@JsonProperty("maxResults") int maxResults,
			@JsonProperty("priceFrom") BigDecimal priceFrom,
			@JsonProperty("priceTo") BigDecimal priceTo,
			@JsonProperty("currency") String currency)
	{
		this.maxResults = maxResults;
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
		this.currency = currency;
	}
	
	public int getMaxResults() {
		return maxResults;
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
}
