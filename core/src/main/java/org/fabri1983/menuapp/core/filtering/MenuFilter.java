package org.fabri1983.menuapp.core.filtering;

import org.fabri1983.menuapp.core.menu.Menu;

public interface MenuFilter {

	boolean accepts(Menu menu);
}
