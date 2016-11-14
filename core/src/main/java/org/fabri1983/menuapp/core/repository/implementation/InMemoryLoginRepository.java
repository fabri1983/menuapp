package org.fabri1983.menuapp.core.repository.implementation;

import org.fabri1983.menuapp.core.repository.LoginRepository;

public class InMemoryLoginRepository implements LoginRepository {

	@Override
	public boolean existUser(long userId) {
		// TODO validate user exists
		return true;
	}

	@Override
	public void loginUser(String userName, String userPassHashed) {
		// TODO throw any validation exception to be grabbed by the service layer
	}

}
