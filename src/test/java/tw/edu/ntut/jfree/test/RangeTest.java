package tw.edu.ntut.jfree.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RangeTest {

    private final double delta = 0.001;

    private Range testRange1;
    private Range testRange2;
    private Range testRange3;

    @Before
    public void setUp() {
        testRange1 = new Range(-10.0, 10.0);
        testRange2 = new Range(0.0, 0.0);
        testRange3 = new Range(-Double.MAX_VALUE, Double.MAX_VALUE);
    }

    //region test public Range(double lower,double upper)
    @Test
    public void testBasicConstructor() {
        Range range = new Range(-10.0, 10.0);
        assertEquals(-10.0, range.getLowerBound(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpperBoundLessThanLowerBoundConstructor() {
        Range range = new Range(10.0, -10.0);
    }
    //endregion

    @Test
    public void testGetLength() {
        assertEquals(20.0, testRange1.getLength(), delta);
        assertEquals(0.0, testRange2.getLength(), delta);
        assertEquals(2*Double.MAX_VALUE, testRange3.getLength(), delta);
    }

    @Test
    public void testGetUpperBound() {
        assertEquals(10.0, testRange1.getUpperBound(), delta);
        assertEquals(0.0, testRange2.getUpperBound(), delta);
        assertEquals(Double.MAX_VALUE, testRange3.getUpperBound(), delta);
    }

    @Test
    public void testGetLowerBound() {
        assertEquals(-10.0, testRange1.getLowerBound(), delta);
        assertEquals(0.0, testRange2.getLowerBound(), delta);
        assertEquals(-Double.MAX_VALUE, testRange3.getLowerBound(), delta);
    }

    //region test public boolean contains(double value)
    @Test
    public void testTestRange1Contains() {
        assertTrue(testRange1.contains(0.0));
        assertTrue(testRange1.contains(10.0));
        assertTrue(testRange1.contains(-10.0));
        assertFalse(testRange1.contains(10.000001));
        assertFalse(testRange1.contains(-10.00001));
        assertFalse(testRange1.contains(Double.MAX_VALUE));
        assertFalse(testRange1.contains(-Double.MAX_VALUE));
    }

    @Test
    public void testTestRange2Contains() {
        assertTrue(testRange2.contains(0.0));
        assertTrue(testRange2.contains(-0.0));
        assertFalse(testRange2.contains(0.00001));
        assertFalse(testRange2.contains(-0.0001));
        assertFalse(testRange2.contains(10.000001));
        assertFalse(testRange2.contains(-10.00001));
        assertFalse(testRange2.contains(Double.MAX_VALUE));
        assertFalse(testRange2.contains(-Double.MAX_VALUE));
    }

    @Test
    public void testTestRange3Contains() {
        assertTrue(testRange3.contains(0.0));
        assertTrue(testRange3.contains(-0.0));
        assertTrue(testRange3.contains(0.00001));
        assertTrue(testRange3.contains(-0.0001));
        assertTrue(testRange3.contains(10.000001));
        assertTrue(testRange3.contains(-10.00001));
        assertTrue(testRange3.contains(Double.MAX_VALUE));
        assertTrue(testRange3.contains(-Double.MAX_VALUE));
    }
    //endregion

}
