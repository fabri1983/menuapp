package org.fabri1983.menuapp.core.filtering.menu.visitor;

import org.fabri1983.menuapp.core.entity.menu.DefaultMenu;
import org.fabri1983.menuapp.core.entity.menu.TimeConstraintMenu;

public interface FilterVisitor {

	boolean canFilterMenu(DefaultMenu menu);
	
	boolean filter(TimeConstraintMenu menu);
	
}
