package org.fabri1983.menuapp.core.converter.menu.visitor;

import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;

/**
 * This interface created to separate conversion decision from the classes where indeed conversion takes place, so the core doesn't know anything about specific converters.
 * Right now only at protocol project there is a menu-to-view converter. 
 * So this interface avoids the need to implement the converter in the core project and implementation only lies in protocol project.
 */
public interface MenuConverterVisitor {

	boolean accepts(DefaultMenu menu);
	
	boolean accepts(TimeConstraintMenu menu);
}
