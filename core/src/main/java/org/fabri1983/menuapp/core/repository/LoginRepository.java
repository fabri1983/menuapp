package org.fabri1983.menuapp.core.repository;

public interface LoginRepository {
	
	boolean existUser(long userId);
	
	void loginUser(String userName, String userPassHashed);
}
