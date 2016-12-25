package org.fabri1983.menuapp.api.config;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import org.fabri1983.menuapp.core.dao.MenuDao;
import org.fabri1983.menuapp.core.dao.implementation.HDFSMenuDao;
import org.fabri1983.menuapp.core.dao.implementation.PreloadedMenuDao;
import org.fabri1983.menuapp.core.dao.implementation.RedisMenuDao;
import org.fabri1983.menuapp.core.repository.LoginRepository;
import org.fabri1983.menuapp.core.repository.MenuRepository;
import org.fabri1983.menuapp.core.repository.implementation.InMemoryLoginRepository;
import org.fabri1983.menuapp.core.repository.implementation.InMemoryMenuRepository;
import org.fabri1983.menuapp.core.service.LoginService;
import org.fabri1983.menuapp.core.service.MenuService;
import org.fabri1983.menuapp.core.service.RatingService;
import org.fabri1983.menuapp.core.service.implementation.SimpleLoginService;
import org.fabri1983.menuapp.core.service.implementation.SimpleMenuService;
import org.fabri1983.menuapp.core.service.implementation.SimpleRatingService;

public class MenuAppProvider implements Module {

    @Override
    public void configure (Binder binder) {
        // add manual bindings here and configure them
    }
    
    @Provides
    @Singleton
    @Inject
    public LoginService getLoggingService(@Named("InMemoryLoginRepository") LoginRepository loginRepository) {
    	return new SimpleLoginService(loginRepository);
    }
    
    @Provides
    @Singleton
    @Inject
    public MenuService getMenuService(@Named("InMemoryMenuRepository") MenuRepository menuRepository) {
    	return new SimpleMenuService(menuRepository);
    }
    
    @Provides
    @Singleton
    @Inject
    public RatingService getRatingService(@Named("InMemoryMenuRepository") MenuRepository menuRepository) {
    	return new SimpleRatingService(menuRepository);
    }
    
    @Provides
    @Singleton
    @Named("InMemoryLoginRepository")
    public LoginRepository getInMemoryLoginRepository() {
    	return new InMemoryLoginRepository();
    }
    
    @Provides
    @Singleton
    @Named("InMemoryMenuRepository")
    @Inject
	public MenuRepository getInMemoryMenuRepository(@Named("PreloadedMenuDao") MenuDao menuDao) {
    	return new InMemoryMenuRepository(menuDao);
    }
    
    @Provides
    @Singleton
    @Named("RedisMenuDao")
    public MenuDao provideRedisMenuDao() {
        return new RedisMenuDao();
    }
    
    @Provides
    @Singleton
    @Named("PreloadedMenuDao")
    public MenuDao providePreloadedMenuDao() {
        return new PreloadedMenuDao();
    }
    
    @Provides
    @Singleton
    @Named("HDFSMenuDao")
    public MenuDao provideHDFSMenuDao() {
        return new HDFSMenuDao();
    }
    
}
