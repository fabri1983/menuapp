package org.fabri1983.menuapp.core.dao.implementation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.fabri1983.menuapp.core.dao.MenuDao;
import org.fabri1983.menuapp.core.dao.converter.JsonPresentationToMenuConverter;
import org.fabri1983.menuapp.core.dao.converter.MenuDaoPresentationConverter;
import org.fabri1983.menuapp.core.dao.presentation.JsonMenuPresentation;
import org.fabri1983.menuapp.core.entity.menu.CurrencyType;

public class PreloadedMenuDao implements MenuDao {

	private Map<Long, JsonMenuPresentation> menusById;
	
	public PreloadedMenuDao ()
	{
		menusById = new HashMap<Long, JsonMenuPresentation>();
		
		JsonMenuPresentation dinnerSuperb = new JsonMenuPresentation(
				1L, "Superb Dinner", "It's an interesting dinner menu.", "http://fabri1983.org/images/dinnerSuperb.png", 
				BigDecimal.valueOf(250), CurrencyType.DEFAULT_USD.name(), 5, 
				LocalTime.of(20,0,0), LocalTime.of(23,0,0), 
				Arrays.asList("Friday", "Saturday", "Sunday"), 
				LocalDateTime.of(2016, 3, 1, 0, 0), LocalDateTime.of(2016, 4, 1, 23, 59));
		menusById.put(dinnerSuperb.getId(), dinnerSuperb);
		
		// FIXME ugly constructor with lots of null! Segregate better!
		JsonMenuPresentation dinnerCheap = new JsonMenuPresentation(
				2L, "Cheapest Dinner", "Are you saving some dimes?", "http://fabri1983.org/images/dinnerCheap.png", 
				BigDecimal.valueOf(80), CurrencyType.DEFAULT_USD.name(), 5, 
				null, null, null, null, null);
		menusById.put(dinnerCheap.getId(), dinnerCheap);
		
		JsonMenuPresentation lunchAmazing = new JsonMenuPresentation(
				3L, "Amazing Lunch", "No one deserve this lunch more than you.", "http://fabri1983.org/images/lunchAmazing.png", 
				BigDecimal.valueOf(150), CurrencyType.DEFAULT_USD.name(), 3, 
				LocalTime.of(12,0,0), LocalTime.of(15,0,0), 
				Arrays.asList("Friday", "Saturday", "Sunday"), 
				LocalDateTime.of(2016, 3, 1, 0, 0), LocalDateTime.of(2016, 4, 1, 23, 59));
		menusById.put(lunchAmazing.getId(), lunchAmazing);
		
		// FIXME ugly constructor with lots of null! Segregate better!
		JsonMenuPresentation lunchNotTasty = new JsonMenuPresentation(
				4L, "Not so tasty lunch", "It's not a taaaaasty lunch. You're warned.", "http://fabri1983.org/images/lunchNotTasty.png", 
				BigDecimal.valueOf(50), CurrencyType.DEFAULT_USD.name(), 3, 
				null, null, null, null, null);
		menusById.put(lunchNotTasty.getId(), lunchNotTasty);
	}
	
	@Override
	public Collection<JsonMenuPresentation> getAll() {
		return menusById.values();
	}

	@Override
	public JsonMenuPresentation getById(long menuId) {
		// FIXME there is an implicit boxing of long parameter to Long. Better use Fast Util api or similar
		return menusById.get(menuId);
	}

	@Override
	public MenuDaoPresentationConverter getConverter () {
		return new JsonPresentationToMenuConverter();
	}
}
