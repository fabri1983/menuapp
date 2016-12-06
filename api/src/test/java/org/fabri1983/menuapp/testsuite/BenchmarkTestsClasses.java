package org.fabri1983.menuapp.testsuite;

import org.junit.extensions.cpsuite.ClasspathSuite;
import org.junit.extensions.cpsuite.ClasspathSuite.ClassnameFilters;
import org.junit.runner.RunWith;

@RunWith(ClasspathSuite.class)
@ClassnameFilters({ BenchmarkTestsClasses.TEST_PACKAGE })
public class BenchmarkTestsClasses {

	public static final String TEST_PACKAGE = "org.fabri1983.menuapp.benchmark.api.*";
}
