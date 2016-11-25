package org.fabri1983.menuapp.core.dao.converter;

import java.net.MalformedURLException;
import java.net.URL;

import org.fabri1983.menuapp.core.dao.representation.JsonMenuPresentation;
import org.fabri1983.menuapp.core.menu.CurrencyType;
import org.fabri1983.menuapp.core.menu.DefaultMenu;
import org.fabri1983.menuapp.core.menu.Menu;
import org.fabri1983.menuapp.core.menu.TimeConstraintMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonPresentationToMenuConverter implements MenuDaoPresentationConverter {

	private Logger logger;
	
	public JsonPresentationToMenuConverter() {
		super();
		logger = LoggerFactory.getLogger(this.getClass());
	}

	@Override
	public Menu convert(JsonMenuPresentation menuRepresentation) {
		if (isTimeConstraintMenu(menuRepresentation))
			return convertToTimeConstraintMenu(menuRepresentation);
		
		return convertToDefaultMenu(menuRepresentation);
	}

	private boolean isTimeConstraintMenu(JsonMenuPresentation menuRepresentation) {
		return menuRepresentation.getHourFrom() != null && menuRepresentation.getHourTo() != null
				&& menuRepresentation.getAvailableDateFrom() != null && menuRepresentation.getAvailableDateTo() != null
				&& menuRepresentation.getAvailableDays() != null && !menuRepresentation.getAvailableDays().isEmpty();
	}
	
	private Menu convertToDefaultMenu(JsonMenuPresentation menuRepresentation) {
		URL pictureURL = null;
		try {
			pictureURL = new URL(menuRepresentation.getPictureUrl());
		} catch (MalformedURLException e) {
			logger.warn("Not valid picture URL provided");
		}
		
		return new DefaultMenu(
				menuRepresentation.getId(),
				menuRepresentation.getName(),
				menuRepresentation.getDescription(),
				pictureURL,
				menuRepresentation.getPrice(),
				CurrencyType.valueOf(menuRepresentation.getCurrency()),
				menuRepresentation.getRating());
	}
	
	private Menu convertToTimeConstraintMenu(JsonMenuPresentation menuRepresentation) {
		URL pictureURL = null;
		try {
			pictureURL = new URL(menuRepresentation.getPictureUrl());
		} catch (MalformedURLException e) {
			logger.warn("Not valid picture URL provided");
		}
		
		return new TimeConstraintMenu(
				menuRepresentation.getId(),
				menuRepresentation.getName(),
				menuRepresentation.getDescription(),
				pictureURL,
				menuRepresentation.getPrice(),
				CurrencyType.valueOf(menuRepresentation.getCurrency()),
				menuRepresentation.getRating(),
				menuRepresentation.getHourFrom(),
				menuRepresentation.getHourTo(),
				menuRepresentation.getAvailableDays(),
				menuRepresentation.getAvailableDateFrom(),
				menuRepresentation.getAvailableDateTo());
	}

}
