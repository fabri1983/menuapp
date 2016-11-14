package org.fabri1983.menuapp.api.provider;

import org.fabri1983.menuapp.api.config.MenuAppConfiguration;
import org.fabri1983.menuapp.core.service.RatingService;
import org.fabri1983.menuapp.core.service.implementation.SimpleRatingService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RatingServiceProvider {

	private RatingService implementation;
	
	@Inject
	public RatingServiceProvider(MenuRepositoryProvider menuRepositoryProvider, MenuAppConfiguration configuration) {
		this.implementation = new SimpleRatingService(menuRepositoryProvider.getImplementation());
	}

	public RatingService getImplementation() {
		return implementation;
	}

}
