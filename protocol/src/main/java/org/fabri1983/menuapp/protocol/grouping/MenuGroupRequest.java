package org.fabri1983.menuapp.protocol.grouping;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.fabri1983.menuapp.protocol.core.MenuGroupingPresentation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuGroupRequest {

	private MenuGroupingPresentation groupData;
	@Min(1)
	private int maxResults;

	@JsonCreator
	public MenuGroupRequest(
			@JsonProperty("groupData") @Valid MenuGroupingPresentation groupData,
			@JsonProperty("maxResults") int maxResults)
	{
		this.groupData = groupData;
		this.maxResults = maxResults;
	}

	public MenuGroupingPresentation getGroupData() {
		return groupData;
	}

	public int getMaxResults() {
		return maxResults;
	}

}
