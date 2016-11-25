package org.fabri1983.menuapp.protocol.menu;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.fabri1983.menuapp.core.menu.CurrencyType;
import org.fabri1983.menuapp.core.view.parserutil.CustomLocalTimeDeserializer;
import org.fabri1983.menuapp.core.view.parserutil.CustomLocalTimeSerializer;
import org.fabri1983.menuapp.protocol.validation.StringEnumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class MenuFiltersView {

	@NotNull @Min(0)
	private BigDecimal maxPrice;
	@NotNull @StringEnumeration(enumClass = CurrencyType.class)
	private String currency;
	private LocalTime hourFrom;
	private LocalTime hourTo;
	private List<String> availableDays;
	private LocalDateTime availableDateFrom;
	private LocalDateTime availableDateTo;

	@JsonCreator
	public MenuFiltersView(
			@JsonProperty("maxPrice") BigDecimal maxPrice,
			@JsonProperty("currency") String currency,
			@JsonSerialize(using = CustomLocalTimeSerializer.class)
			@JsonDeserialize(using = CustomLocalTimeDeserializer.class)
			@JsonProperty("hourFrom") LocalTime hourFrom,
			@JsonSerialize(using = CustomLocalTimeSerializer.class)
			@JsonDeserialize(using = CustomLocalTimeDeserializer.class)
			@JsonProperty("hourTo") LocalTime hourTo,
			@JsonProperty("availableDays") List<String> availableDays,
			@JsonSerialize(using = LocalDateTimeSerializer.class)
			@JsonDeserialize(using = LocalDateTimeDeserializer.class)
			@JsonProperty("availableDateFrom") LocalDateTime availableDateFrom,
			@JsonSerialize(using = LocalDateTimeSerializer.class)
			@JsonDeserialize(using = LocalDateTimeDeserializer.class)
			@JsonProperty("availableDateTo") LocalDateTime availableDateTo)
	{	
		this.maxPrice = maxPrice;
		this.currency = currency;
		this.hourFrom = hourFrom;
		this.hourTo = hourTo;
		this.availableDays = availableDays;
		this.availableDateFrom = availableDateFrom;
		this.availableDateTo = availableDateTo;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public CurrencyType getCurrency() {
		return CurrencyType.valueOf(currency);
	}
	
	public LocalTime getHourFrom() {
		return hourFrom;
	}

	public LocalTime getHourTo() {
		return hourTo;
	}

	public List<String> getAvailableDays() {
		return availableDays;
	}

	public LocalDateTime getAvailableDateFrom() {
		return availableDateFrom;
	}

	public LocalDateTime getAvailableDateTo() {
		return availableDateTo;
	}

	public boolean hasPriceFilter() {
		return maxPrice != null;
	}

	public boolean hasAvailableHoursFilter() {
		return hourFrom != null && hourTo != null;
	}

	public boolean hasAvailableDaysFilter() {
		return availableDays != null && !availableDays.isEmpty();
	}

	public boolean hasAvailableDateFilter() {
		return availableDateFrom != null && availableDateTo != null;
	}

}