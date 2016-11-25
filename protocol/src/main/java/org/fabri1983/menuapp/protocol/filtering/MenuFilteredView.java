package org.fabri1983.menuapp.protocol.filtering;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.fabri1983.menuapp.protocol.menu.MenuFiltersView;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuFilteredView {

	@NotNull @Valid
	private MenuFiltersView filterData;
	@Min(1) @Max(50)
	private int maxResults;

	@JsonCreator
	public MenuFilteredView(
			@JsonProperty("filterData") MenuFiltersView filterData,
			@JsonProperty("maxResults") int maxResults)
	{
		this.filterData = filterData;
		this.maxResults = maxResults;
	}

	public MenuFiltersView getFilterData() {
		return filterData;
	}

	public int getMaxResults() {
		return maxResults;
	}

}
