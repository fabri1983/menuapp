package org.fabri1983.menuapp.api.resource;

import com.google.inject.Inject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fabri1983.menuapp.core.service.LoginService;
import org.fabri1983.menuapp.protocol.login.LoginSuccessfulView;
import org.fabri1983.menuapp.protocol.login.LoginView;

@Path("/user/login")
@Api(value = "LoginResource")
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

	private LoginService loginService;
	
	@Inject
	public LoginResource(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Logins the user with supplied credentials. Returns generated token", response = LoginSuccessfulView.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successful login info with generated token", response = LoginSuccessfulView.class),
	        @ApiResponse(code = 401, message = "Authorization has been refused"),
			@ApiResponse(code = 422, message = "Unprocessable entity. Validation failed")}
	    )
	public Response login(
			@NotNull @Valid LoginView loginView)
	{
		String token = loginService.loginUser(loginView.getUserName(), loginView.getUserPassHashed());

		// TODO store user location with an UserService or similar service
		
		LoginSuccessfulView response = LoginSuccessfulView.create(loginView, token);
		return Response.status(Response.Status.OK).entity(response).build();
	}
}
