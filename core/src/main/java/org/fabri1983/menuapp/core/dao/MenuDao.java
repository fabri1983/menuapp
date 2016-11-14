package org.fabri1983.menuapp.core.dao;

import java.util.Collection;

import org.fabri1983.menuapp.core.dao.converter.MenuDaoPresentationConverter;
import org.fabri1983.menuapp.core.dao.representation.JsonMenuPresentation;

public interface MenuDao {

	Collection<JsonMenuPresentation> getAll ();
	
	JsonMenuPresentation getById (long menuId);
	
	MenuDaoPresentationConverter getConverter();
}
