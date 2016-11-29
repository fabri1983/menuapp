package org.fabri1983.menuapp.protocol.menu;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.fabri1983.menuapp.core.entity.menu.CurrencyType;
import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.protocol.parserutil.CustomLocalTimeDeserializer;
import org.fabri1983.menuapp.protocol.parserutil.CustomLocalTimeSerializer;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@JsonInclude(value = JsonInclude.Include.NON_NULL) // use this in order to exclude those fields having null values when serializing
public class MenuView {

	@JsonProperty
	private long id;
	@JsonProperty @NotEmpty
	private String name;
	@JsonProperty @NotEmpty
	private String description;
	@JsonProperty @NotEmpty
	private String pictureUrl;
	@JsonProperty @NotNull
	private BigDecimal price;
	@JsonProperty @NotNull
	private CurrencyType currency;
	@JsonProperty @Min(1) @Max(5)
	private int rating;
	@JsonSerialize(using = CustomLocalTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalTimeDeserializer.class)
	private LocalTime hourFrom;
	@JsonSerialize(using = CustomLocalTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalTimeDeserializer.class)
	private LocalTime hourTo;
	@JsonProperty
	private List<String> availableDays;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime availableDateFrom;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime availableDateTo;
	
	public static MenuView from (DefaultMenu menu) {
		return new MenuView(menu.getId(), menu.getName(), menu.getDescription(), menu.getPictureUrl().toString(), menu.getPrice(), menu.getCurrency(), menu.getRating());
	}
	
	private MenuView(long id, String name, String description, String pictureUrl, BigDecimal price, CurrencyType currency, int rating)
	{	
		this.id = id;
		this.name = name;
		this.description = description;
		this.pictureUrl = pictureUrl;
		this.price = price;
		this.currency = currency;
		this.rating = rating;
	}
	
	public static MenuView from (TimeConstraintMenu menu) {
		return new MenuView(menu.getId(), menu.getName(), menu.getDescription(), menu.getPictureUrl().toString(), menu.getPrice(), menu.getCurrency(), menu.getRating(),
				menu.getHourFrom(), menu.getHourTo(), menu.getAvailableDays(), menu.getAvailableDateFrom(), menu.getAvailableDateTo());
	}
	
	private MenuView(long id, String name, String description, String pictureUrl, BigDecimal price, CurrencyType currency, int rating, LocalTime hourFrom, 
			LocalTime hourTo, List<String> availableDays, LocalDateTime availableDateFrom, LocalDateTime availableDateTo)
	{	
		this.id = id;
		this.name = name;
		this.description = description;
		this.pictureUrl = pictureUrl;
		this.price = price;
		this.currency = currency;
		this.rating = rating;
		this.hourFrom = hourFrom;
		this.hourTo = hourTo;
		this.availableDays = availableDays;
		this.availableDateFrom = availableDateFrom;
		this.availableDateTo = availableDateTo;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public CurrencyType getCurrency() {
		return currency;
	}
	
	public int getRating() {
		return rating;
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
	
}