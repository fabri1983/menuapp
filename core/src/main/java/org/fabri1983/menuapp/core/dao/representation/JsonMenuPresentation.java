package org.fabri1983.menuapp.core.dao.representation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import io.dropwizard.jackson.JsonSnakeCase;

@JsonSnakeCase
public class JsonMenuPresentation {

	@JsonProperty
	private long id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String description;
	@JsonProperty
	private String pictureUrl;
	@JsonProperty
	private BigDecimal price;
	@JsonProperty
	private String currency;
	@JsonProperty
	private int rating;
	@JsonSerialize(using = LocalTimeSerializer.class)
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	private LocalTime hourFrom;
	@JsonSerialize(using = LocalTimeSerializer.class)
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	private LocalTime hourTo;
	@JsonProperty
	private List<String> availableDays;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime availableDateFrom;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime availableDateTo;

	public JsonMenuPresentation(long id, String name, String description, String pictureUrl, BigDecimal price,
			String currency, int rating, LocalTime hourFrom, LocalTime hourTo, List<String> availableDays,
			LocalDateTime availableDateFrom, LocalDateTime availableDateTo) {
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

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public LocalTime getHourFrom() {
		return hourFrom;
	}

	public void setHourFrom(LocalTime hourFrom) {
		this.hourFrom = hourFrom;
	}

	public LocalTime getHourTo() {
		return hourTo;
	}

	public void setHourTo(LocalTime hourTo) {
		this.hourTo = hourTo;
	}

	public List<String> getAvailableDays() {
		return availableDays;
	}

	public void setAvailableDays(List<String> availableDays) {
		this.availableDays = availableDays;
	}

	public LocalDateTime getAvailableDateFrom() {
		return availableDateFrom;
	}

	public void setAvailableDateFrom(LocalDateTime availableDateFrom) {
		this.availableDateFrom = availableDateFrom;
	}

	public LocalDateTime getAvailableDateTo() {
		return availableDateTo;
	}

	public void setAvailableDateTo(LocalDateTime availableDateTo) {
		this.availableDateTo = availableDateTo;
	}

}
