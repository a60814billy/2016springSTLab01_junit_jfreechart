package tw.edu.ntut.jfree.test;

import tw.edu.ntut.jfree.test.io.CSVTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataUtilitiesUnitTest.class,
        CSVTest.class,
        RangeTest.class
})
public class JFreeChartDataTestSuite {
}
