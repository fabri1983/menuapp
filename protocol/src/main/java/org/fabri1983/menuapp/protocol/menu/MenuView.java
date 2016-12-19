package org.fabri1983.menuapp.protocol.menu;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.fabri1983.menuapp.core.entity.menu.CurrencyType;
import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.Menu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;
import org.fabri1983.menuapp.protocol.menu.converter.MenuViewConverterResolver;
import org.fabri1983.menuapp.protocol.parserutil.CustomLocalTimeDeserializer;
import org.fabri1983.menuapp.protocol.parserutil.CustomLocalTimeSerializer;
import org.fabri1983.menuapp.protocol.validation.StringEnumeration;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

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

@ApiModel(value="MenuView", description="Presentation of a menu request/response")
@JsonInclude(value = JsonInclude.Include.NON_NULL) // use this in order to exclude those fields having null values when serializing
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
	
	public static MenuView convert (final Menu menu) {
		return MenuViewConverterResolver.convert(menu);
	}
	
	public static List<MenuView> convert(Collection<Menu> filteredMenus, int limitSize) {
		return filteredMenus.stream()
				.limit(limitSize)
				.map( MenuView::convert )
				.collect(Collectors.toList());
	}
	
	public static MenuView from (final DefaultMenu menu) {
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
	
	public static MenuView from (final TimeConstraintMenu menu) {
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
