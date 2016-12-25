package org.fabri1983.menuapp.api.resource;

import com.google.inject.Inject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fabri1983.menuapp.api.config.hasfeature.HasBuildInfoFeature;
import org.fabri1983.menuapp.api.config.hasfeature.impl.BuildInfoConfig;

@Path("/info")
@Api(value = "InfoResource")
public class InfoResource {

	private BuildInfoConfig buildInfoConfig;
	
	@Inject
	public InfoResource(HasBuildInfoFeature hasBuildInfoFeature) {
		this.buildInfoConfig = hasBuildInfoFeature.getBuildInfoConfig();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Returns build information details", response = String.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successful retrieval of build info", response = String.class)}
	    )
	@Path("/buildinfo")
	public Response getBuildInfo()
	{
		String buildInfo = buildInfoConfig.getBuildInfo();
		return Response.status(Response.Status.OK).entity(buildInfo).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Returns build profile name", response = String.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successful retrieval of profile name", response = String.class)}
	    )
	@Path("/profile")
	public Response getBuildProfile()
	{
		String buildProfile = buildInfoConfig.getBuildProfile();
		return Response.status(Response.Status.OK).entity(buildProfile).build();
	}
}
