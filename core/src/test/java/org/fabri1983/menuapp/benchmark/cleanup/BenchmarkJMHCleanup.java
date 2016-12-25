package org.fabri1983.menuapp.benchmark.cleanup;

import org.junit.ClassRule;
import org.junit.Test;

import pl.wavesoftware.jmh.junit.utilities.JmhCleaner;

public class BenchmarkJMHCleanup {

	@ClassRule
	public static JmhCleaner cleaner = new JmhCleaner(BenchmarkJMHCleanup.class);
	
	@Test
	public void forceClassRuleExecution() {
		// we need at least one test to ensure the cleanup by JmhCleaner
	}
}
