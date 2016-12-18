package org.fabri1983.menuapp.api.resource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fabri1983.menuapp.core.service.LoginService;
import org.fabri1983.menuapp.protocol.login.LoginSuccessfulView;
import org.fabri1983.menuapp.protocol.login.LoginView;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("user/login")
@Api(value = "LoginResource")
public class LoginResource {

	private LoginService loginService;
	
	@Inject
	public LoginResource (LoginService loginService) {
		this.loginService = loginService;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Timed
	@ApiOperation(value = "Logins the user with supplied credentials. Returns the current token", response = LoginSuccessfulView.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successful login. Returns current token", response = LoginSuccessfulView.class),
	        @ApiResponse(code = 401, message = "User not authorized")}
	    )
	public Response login (
			@NotNull @Valid LoginView loginView)
	{
		loginService.loginUser(loginView.getUserName(), loginView.getUserPassHashed());

		// TODO store user location with an UserService or similar service
		
		// TODO use a Token Generator service
		String token = "h0t6dSh8gR5mBpMF3EWWJospF6usI8RLGWIOGCe5Z2HtKu32BBviWrt9wbnO21JICFXKYddYotB79ckrCVRv2z71PFlavOkeDD2JyiueYupdx87DwVpCox58KkQ2kwPb";
		
		LoginSuccessfulView response = LoginSuccessfulView.create(loginView, token);
		return Response.status(Response.Status.OK).entity(response).build();
	}
}
