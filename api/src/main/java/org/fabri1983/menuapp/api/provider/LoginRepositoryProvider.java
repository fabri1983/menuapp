package org.fabri1983.menuapp.api.provider;

import org.fabri1983.menuapp.core.repository.LoginRepository;
import org.fabri1983.menuapp.core.repository.implementation.InMemoryLoginRepository;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LoginRepositoryProvider {

	private LoginRepository implementation;
	
	@Inject
	public LoginRepositoryProvider () {
		this.implementation = new InMemoryLoginRepository();
	}

	public LoginRepository getImplementation() {
		return implementation;
	}
	
}
