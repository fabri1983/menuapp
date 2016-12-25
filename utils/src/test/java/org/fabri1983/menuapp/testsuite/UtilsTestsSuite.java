package org.fabri1983.menuapp.testsuite;

import org.junit.extensions.cpsuite.ClasspathSuite;
import org.junit.extensions.cpsuite.ClasspathSuite.ClassnameFilters;
import org.junit.runner.RunWith;

@RunWith(ClasspathSuite.class)
@ClassnameFilters({ UtilsTestsSuite.TEST_PACKAGE })
public class UtilsTestsSuite {

	public static final String TEST_PACKAGE = "org.fabri1983.menuapp.utils.*";
}
