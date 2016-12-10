package org.fabri1983.menuapp.api.provider;

import org.fabri1983.menuapp.core.service.LoginService;
import org.fabri1983.menuapp.core.service.implementation.SimpleLoginService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LoginServiceProvider {

	private LoginService implementation;
	
	@Inject
	public LoginServiceProvider(LoginRepositoryProvider loginRepositoryProvider) {
		this.implementation = new SimpleLoginService(loginRepositoryProvider.getImplementation());
	}

	public LoginService getImplementation() {
		return implementation;
	}

}
