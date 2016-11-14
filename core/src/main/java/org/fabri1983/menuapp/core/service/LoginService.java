package org.fabri1983.menuapp.core.service;

public interface LoginService {
	
	boolean existUser (long userId);

	void loginUser(String userName, String userPassHashed);
}
