package org.fabri1983.menuapp.core.dao.converter;

import org.fabri1983.menuapp.core.dao.representation.JsonMenuPresentation;
import org.fabri1983.menuapp.core.menu.Menu;

public interface MenuDaoPresentationConverter {

	Menu convert(JsonMenuPresentation menuRepresentation);
	
	// FIXME can't add one converter method per different representation. It's a good opportunity for a double dispatch pattern
}
