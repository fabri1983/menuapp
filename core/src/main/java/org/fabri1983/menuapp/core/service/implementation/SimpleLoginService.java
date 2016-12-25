package org.fabri1983.menuapp.core.service.implementation;

import org.fabri1983.menuapp.core.repository.LoginRepository;
import org.fabri1983.menuapp.core.service.LoginService;

public class SimpleLoginService implements LoginService {

	LoginRepository loginRepository;
	
	public SimpleLoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	@Override
	public boolean existUser(long userId) {
		return loginRepository.existUser(userId);
	}

	@Override
	public String loginUser(String userName, String userPassHashed) {
		loginRepository.loginUser(userName, userPassHashed);
		
		// TODO use a Token Generator service
		String token = "h0t6dSh8gR5mBpMF3EWWJospF6usI8RLGWIOGCe5Z2HtKu32BBviWrt9wbnO21JICFXKYddYot";
		
		return token;
	}

}
