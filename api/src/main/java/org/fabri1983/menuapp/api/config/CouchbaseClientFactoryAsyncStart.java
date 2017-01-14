package org.fabri1983.menuapp.api.config;

import io.smartmachine.couchbase.CouchbaseClientFactory;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.vyarus.dropwizard.guice.module.installer.scanner.InvisibleForScanner;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Custom Couchbase Client Factory which fires the initial connection to Couchbase in a separate thread. 
 * 
 * This class uses {@link InvisibleForScanner} annotation. If not, guice installer will add it to the 
 * jersey managed life cycle with all values as null.
 */
@InvisibleForScanner
public class CouchbaseClientFactoryAsyncStart extends CouchbaseClientFactory {

	private Logger logger = LoggerFactory.getLogger(CouchbaseClientFactory.class);
	
	public CouchbaseClientFactoryAsyncStart() {
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	@Override
	public void start() {
		Observable	.fromCallable(callableStart())
        			.subscribeOn(Schedulers.newThread())
        			.map(passConnectionStatus())
        			.subscribe(handleConnectionStatus());
	}

	private Callable<Integer> callableStart() {
	    return () -> {
	    	try {
	    		logger.info("############################################### -------- STARTING ASYNC Couchbase connection!");
	    		super.start();
	    		return 0;
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		return 1;
	    	}
	    };
	}
	
	private Func1<Integer, Integer> passConnectionStatus() {
	    return status -> {
	    	return status;
	    };
	}
	
	private Action1<Integer> handleConnectionStatus() {
	    return status -> {
	    	if (0 == status.intValue()) {
	    		logger.info("############################################### -------- CONNECTED!");
	    	} else{
	    		try {
	    			logger.error("############################################### -------- PROBLEM! DISCONNECTING...");
					super.stop();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
	    	}
	    };
	}
}
