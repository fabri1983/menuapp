package org.fabri1983.menuapp.protocol.filtering;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.fabri1983.menuapp.protocol.core.MenuFiltersPresentation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuFilteredRequest {

	@NotNull
	private MenuFiltersPresentation filterData;
	@Min(1)
	private int maxResults;

	@JsonCreator
	public MenuFilteredRequest(
			@JsonProperty("filterData") @Valid MenuFiltersPresentation filterData,
			@JsonProperty("maxResults") int maxResults)
	{
		this.filterData = filterData;
		this.maxResults = maxResults;
	}

	public MenuFiltersPresentation getFilterData() {
		return filterData;
	}

	public int getMaxResults() {
		return maxResults;
	}

}
