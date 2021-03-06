package org.fabri1983.menuapp.benchmark.core.service;

import org.fabri1983.menuapp.benchmark.JMHLauncherJUnit;
import org.fabri1983.menuapp.core.dao.MenuDao;
import org.fabri1983.menuapp.core.dao.implementation.PreloadedMenuDao;
import org.fabri1983.menuapp.core.repository.MenuRepository;
import org.fabri1983.menuapp.core.repository.implementation.InMemoryMenuRepository;
import org.fabri1983.menuapp.core.service.RatingService;
import org.fabri1983.menuapp.core.service.implementation.SimpleRatingService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

@State(Scope.Thread)
public class RatingServiceBenchmark extends JMHLauncherJUnit {
	
	@BeforeClass
	public static void launchBenchmark() throws RunnerException {
		if (isJunitInitialized()) {
			return;
		}
		
		// use JMHLauncherJUnit.BenchmarkState to add lambdas action which will be executed according the @Setup level.
		
		launchBenchmark(RatingServiceBenchmark.class.getSimpleName());
	}
	
	@Setup(Level.Trial)
	public void initialize() {
	}
	
	@TearDown(Level.Trial)
    public void doTearDown() {
    }
	
	@Test
	public void forceLaunch() throws RunnerException {
		// empty test to trigger the execution of the benchmark
	}
	
	@Benchmark
	public void getRatingFromMenu(Blackhole blackHole) {
		MenuDao menuDao = new PreloadedMenuDao();
		MenuRepository menuRepository = new InMemoryMenuRepository(menuDao);
		RatingService ratingService = new SimpleRatingService(menuRepository);
		
		ratingService.getRatingFromMenu(1);
	}
	
	@Benchmark
	public void updateRating(Blackhole blackHole) {
		MenuDao menuDao = new PreloadedMenuDao();
		MenuRepository menuRepository = new InMemoryMenuRepository(menuDao);
		RatingService ratingService = new SimpleRatingService(menuRepository);
		
		ratingService.updateRating(1, 5);
	}
}
