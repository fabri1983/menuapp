package org.fabri1983.menuapp.protocol.grouping;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.fabri1983.menuapp.protocol.menu.MenuGroupingView;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuGroupView {

	private MenuGroupingView groupData;
	private int maxResults;

	@JsonCreator
	public MenuGroupView(
			@JsonProperty("groupData") @NotNull @Valid MenuGroupingView groupData,
			@JsonProperty("maxResults") @Min(1) int maxResults)
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
