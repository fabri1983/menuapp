package org.fabri1983.menuapp.core.dao.implementation;

import java.util.Collection;
import java.util.Collections;

import org.fabri1983.menuapp.core.dao.MenuDao;
import org.fabri1983.menuapp.core.dao.converter.JsonRepresentationToMenuConverter;
import org.fabri1983.menuapp.core.dao.converter.MenuDaoPresentationConverter;
import org.fabri1983.menuapp.core.dao.representation.JsonMenuPresentation;

/**
 * Menu entities are stored and retrieved from a Redis DB.
 */
public class RedisMenuDao implements MenuDao {

	// TODO add instance field for Redis DB access. Inject it via constructor
	
	@Override
	public Collection<JsonMenuPresentation> getAll() {
		// TODO add logic here
		return Collections.emptyList();
	}
	
	@Override
	public JsonMenuPresentation getById(long menuId) {
		// TODO retrieve from Redis 
		// Here you can discern whether the type of Menu it's simple or time constraint or whatever,
		// and define the way the menu representation will be populated.
		// Eg: simple menus are stored in a different table than any other type of menu due to disk space restrictions
		// so you have to query at least both tables to then create the representation object as a whole.
		
		return null;
	}

	@Override
	public MenuDaoPresentationConverter getConverter () {
		// we store data in Redis in Json format
		return new JsonRepresentationToMenuConverter();
	}
}
