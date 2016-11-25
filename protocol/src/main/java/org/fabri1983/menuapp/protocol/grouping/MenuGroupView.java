package org.fabri1983.menuapp.protocol.grouping;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.fabri1983.menuapp.protocol.menu.MenuGroupingView;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuGroupView {

	@NotNull @Valid
	private MenuGroupingView groupData;
	@Min(1) @Max(50)
	private int maxResults;

	@JsonCreator
	public MenuGroupView(
			@JsonProperty("groupData") MenuGroupingView groupData,
			@JsonProperty("maxResults") int maxResults)
	{
		this.groupData = groupData;
		this.maxResults = maxResults;
	}

	public MenuGroupingView getGroupData() {
		return groupData;
	}

	public int getMaxResults() {
		return maxResults;
	}

}
