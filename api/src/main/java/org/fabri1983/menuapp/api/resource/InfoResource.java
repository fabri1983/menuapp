package org.fabri1983.menuapp.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fabri1983.menuapp.api.config.hasfeature.HasBuildInfoFeature;
import org.fabri1983.menuapp.api.config.hasfeature.impl.BuildInfoConfig;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;

@Path("")
@Produces(MediaType.TEXT_PLAIN)
public class InfoResource {

	private BuildInfoConfig buildInfoConfig;
	
	@Inject
	public InfoResource (HasBuildInfoFeature hasBuildInfoFeature) {
		this.buildInfoConfig = hasBuildInfoFeature.getBuildInfoConfig();
	}

	@GET
    @Timed
    @Path("/buildinfo")
	public Response getBuildInfo () {
		return Response.status(Response.Status.OK).entity(buildInfoConfig.getBuildInfo()).build();
	}
	
	@GET
    @Timed
    @Path("/profile")
	public Response getBuildProfile () {
		return Response.status(Response.Status.OK).entity(buildInfoConfig.getBuildProfile()).build();
	}
}
