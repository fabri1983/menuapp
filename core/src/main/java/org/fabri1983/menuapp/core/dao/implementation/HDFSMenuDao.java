package org.fabri1983.menuapp.core.dao.implementation;

import java.util.Collection;
import java.util.Collections;

import org.fabri1983.menuapp.core.dao.MenuDao;
import org.fabri1983.menuapp.core.dao.converter.JsonPresentationToMenuConverter;
import org.fabri1983.menuapp.core.dao.converter.MenuDaoPresentationConverter;
import org.fabri1983.menuapp.core.dao.presentation.JsonMenuPresentation;

/**
 * Menu entities are stored and retrieved from a Hadoop Distributed File System.
 */
public class HDFSMenuDao implements MenuDao {

	// TODO add instance field for HDFS access. Inject it via constructor
	
	@Override
	public Collection<JsonMenuPresentation> getAll() {
		// TODO add logic here
		return Collections.emptyList();
	}
	
	@Override
	public JsonMenuPresentation getById(long menuId) {
		// TODO retrieve from HDFS 
		// Here you can discern whether the type of Menu it's simple or time constraint or whatever,
		// and define the way the menu representation will be populated.
		// Eg: simple menus are stored in a different table than any other type of menu due to disk space restrictions
		// so you have to query at least both tables to then create the representation object as a whole.
		
		return null;
	}

	@Override
	public MenuDaoPresentationConverter getConverter () {
		// we store data in HDFS as Json format documents
		return new JsonPresentationToMenuConverter();
	}
}
