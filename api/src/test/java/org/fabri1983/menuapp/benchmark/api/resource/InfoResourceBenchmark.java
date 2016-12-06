package org.fabri1983.menuapp.benchmark.api.resource;

import org.fabri1983.menuapp.benchmark.JMHLauncherJUnit;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

public class InfoResourceBenchmark extends JMHLauncherJUnit {
	
	@BeforeClass
	public static void setup () {
		// use JMHLauncherJUnit.BenchmarkState to add lambdas action which weel be executed according the @Setup level.
	}
	
	@Test
	public void launchBenchmark () throws RunnerException {
		launchBenchmark(this.getClass().getName());
	}
	
	@Benchmark
	public void requestBuildinfo (BenchmarkState state, Blackhole bh) {
		
	}
	
	@Benchmark
	public void requestProfile (BenchmarkState state, Blackhole bh) {
		
	}
}
