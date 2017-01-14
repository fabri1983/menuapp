package org.fabri1983.menuapp.protocol.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.fabri1983.menuapp.core.entity.menu.CurrencyType;
import org.fabri1983.menuapp.protocol.parserutil.CustomLocalTimeDeserializer;
import org.fabri1983.menuapp.protocol.parserutil.CustomLocalTimeSerializer;
import org.fabri1983.menuapp.protocol.validation.StringEnumeration;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@ApiModel(value="MenuView", description="Presentation of a menu request/response")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonNaming(LowerCaseStrategy.class)
public class MenuView {

	@ApiModelProperty(value = "id value", dataType = "long", required = true)
	@JsonProperty
	private long id;
	
	@ApiModelProperty(value = "name value", dataType = "String", required = true)
	@JsonProperty @NotEmpty
	private String name;
	
	@ApiModelProperty(value = "description value", dataType = "String", allowableValues = "Text with a max of 255 chars", required = true)
	@JsonProperty @NotEmpty @Length(max = 255)
	private String description;
	
	@ApiModelProperty(value = "pictureUrl value", dataType = "String", required = true)
	@JsonProperty @NotEmpty
	private String pictureUrl;
	
	@ApiModelProperty(value = "price value", dataType = "java.math.BigDecimal", required = true)
	@JsonProperty @NotNull
	private BigDecimal price;
	
	@ApiModelProperty(value = "currency value", dataType = "org.fabri1983.menuapp.core.entity.menu.CurrencyType", required = true)
	@JsonProperty @NotNull @StringEnumeration(enumClass = CurrencyType.class)
	private CurrencyType currency;
	
	@ApiModelProperty(value = "rating value between 1 and 5", dataType = "int", allowableValues = "range[1, 5]", required = true)
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

	public CurrencyType getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyType currency) {
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
