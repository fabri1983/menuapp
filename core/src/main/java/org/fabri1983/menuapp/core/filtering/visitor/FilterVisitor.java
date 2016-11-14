package org.fabri1983.menuapp.core.filtering.visitor;

import org.fabri1983.menuapp.core.menu.DefaultMenu;
import org.fabri1983.menuapp.core.menu.TimeConstraintMenu;

public interface FilterVisitor {

	boolean canFilterMenu (DefaultMenu menu);
	
	boolean filter (TimeConstraintMenu menu);
	
}
