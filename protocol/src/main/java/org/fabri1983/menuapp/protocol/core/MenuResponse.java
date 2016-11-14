package org.fabri1983.menuapp.protocol.core;

import java.util.List;

import org.fabri1983.menuapp.core.presentation.MenuPresentation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuResponse {
	
	@JsonProperty
	private List<MenuPresentation> menus;

	public MenuResponse(List<MenuPresentation> menus) {
		this.menus = menus;
	}

	public List<MenuPresentation> getMenus() {
		return menus;
	}

}
