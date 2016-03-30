package tw.edu.ntut.jfree.test;

import org.jfree.data.Range;
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
    public void testGetLength1() {
        assertEquals(20.0, testRange1.getLength(), delta);
    }

    @Test
    public void testGetLength2() {
        assertEquals(0.0, testRange2.getLength(), delta);
    }

    @Test
    public void testGetLength3() {
        assertEquals(2 * Double.MAX_VALUE, testRange3.getLength(), delta);
    }

    @Test
    public void testGetUpperBound1() {
        assertEquals(10.0, testRange1.getUpperBound(), delta);
    }

    @Test
    public void testGetUpperBound2() {
        assertEquals(0.0, testRange2.getUpperBound(), delta);
    }

    @Test
    public void testGetUpperBound3() {
        assertEquals(Double.MAX_VALUE, testRange3.getUpperBound(), delta);
    }

    @Test
    public void testGetLowerBound1() {
        assertEquals(-10.0, testRange1.getLowerBound(), delta);
    }

    @Test
    public void testGetLowerBound2() {
        assertEquals(0.0, testRange2.getLowerBound(), delta);
    }

    @Test
    public void testGetLowerBound3() {
        assertEquals(-Double.MAX_VALUE, testRange3.getLowerBound(), delta);
    }

    //region test public boolean contains(double value)
    @Test
    public void testTestRange1Contains1() {
        assertTrue(testRange1.contains(0.0));
    }

    @Test
    public void testTestRange1Contains2() {
        assertTrue(testRange1.contains(10.0));
    }

    @Test
    public void testTestRange1Contains3() {
        assertTrue(testRange1.contains(-10.0));
    }

    @Test
    public void testTestRange1Contains4() {
        assertFalse(testRange1.contains(10.000001));
    }

    @Test
    public void testTestRange1Contains5() {
        assertFalse(testRange1.contains(-10.00001));
    }

    @Test
    public void testTestRange1Contains6() {
        assertFalse(testRange1.contains(Double.MAX_VALUE));
    }

    @Test
    public void testTestRange1Contains7() {
        assertFalse(testRange1.contains(-Double.MAX_VALUE));
    }

    @Test
    public void testTestRange2Contains8() {
        assertTrue(testRange2.contains(0.0));
    }

    @Test
    public void testTestRange2Contains9() {
        assertTrue(testRange2.contains(-0.0));
    }

    @Test
    public void testTestRange2Contains10() {
        assertFalse(testRange2.contains(0.00001));
    }

    @Test
    public void testTestRange2Contains11() {
        assertFalse(testRange2.contains(-0.0001));
    }

    @Test
    public void testTestRange2Contains12() {
        assertFalse(testRange2.contains(10.000001));
    }

    @Test
    public void testTestRange2Contains13() {
        assertFalse(testRange2.contains(-10.00001));
    }

    @Test
    public void testTestRange2Contains14() {
        assertFalse(testRange2.contains(Double.MAX_VALUE));
    }

    @Test
    public void testTestRange2Contains15() {
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

    @Test
    public void testIntersectsWithTwoDouble1() {
        assertTrue(testRange1.intersects(-9.0, 9.0));
    }

    @Test
    public void testIntersectsWithTwoDouble2() {
        assertTrue(testRange1.intersects(-10.0, 10.0));
    }

    @Test
    public void testIntersectsWithTwoDouble3() {
        assertTrue(testRange1.intersects(-10.0, 10.1));
    }

    @Test
    public void testIntersectsWithTwoDouble4() {
        assertTrue(testRange1.intersects(-10.1, 10.0));
    }

    @Test
    public void testIntersectsWithTwoDouble5() {
        assertFalse(testRange1.intersects(10.1, 11.0));
    }

    @Test
    public void testIntersectsWithTwoDouble6() {
        assertFalse(testRange1.intersects(-11.0, -10.9));
    }

    @Test
    public void testIntersectsWithTwoDouble7() {
        assertFalse(testRange1.intersects(10.0, -10.0));
    }

    @Test
    public void testIntersectsWithRange1() {
        assertTrue(testRange1.intersects(new Range(-9.0, 9.0)));
    }
    @Test
    public void testIntersectsWithRange2() {
        assertTrue(testRange1.intersects(new Range(-10.0, 10.0)));
    }
    @Test
    public void testIntersectsWithRange3() {
        assertTrue(testRange1.intersects(new Range(-10.1, 10.0)));
    }
    @Test
    public void testIntersectsWithRange4() {
        assertTrue(testRange1.intersects(new Range(-10.1, 10.1)));
    }
    @Test
    public void testIntersectsWithRange5() {
        assertFalse(testRange1.intersects(new Range(10.1, 11.0)));
    }
    @Test
    public void testIntersectsWithRange6() {
        assertFalse(testRange1.intersects(new Range(-11.0, -10.1)));
    }

    @Test
    public void testConstrain1() {
        assertEquals(-1.0, testRange1.constrain(-1.0), delta);
    }
    @Test
    public void testConstrain2() {
        assertEquals(-10.0, testRange1.constrain(-10.0), delta);
    }
    @Test
    public void testConstrain3() {
        assertEquals(-0.0000001, testRange1.constrain(-0.0000001), delta);
    }
    @Test
    public void testConstrain4() {
        assertEquals(0.000001, testRange1.constrain(0.000001), delta);
    }
    @Test
    public void testConstrain5() {
        assertEquals(1.0, testRange1.constrain(1.0), delta);
    }
    @Test
    public void testConstrain6() {
        assertEquals(10.0, testRange1.constrain(10.0), delta);
    }
    @Test
    public void testConstrain7() {
        assertEquals(10.0, testRange1.constrain(100.0), delta);
    }
    @Test
    public void testConstrain8() {
        assertEquals(-10.0, testRange1.constrain(-100.0), delta);
    }
    @Test
    public void testConstrain9() {
        assertEquals(10.0, testRange1.constrain(Double.MAX_VALUE), delta);
    }
    @Test
    public void testConstrain10() {
        assertEquals(-10.0, testRange1.constrain(-Double.MAX_VALUE), delta);
    }

    @Test
    public void testGetCentralValue1() {
        assertEquals(0.0, testRange1.getCentralValue(), delta);
    }

    @Test
    public void testGetCentralValue2() {
        assertEquals(0.0, testRange2.getCentralValue(), delta);
    }

    @Test
    public void testGetCentralValue3() {
        assertEquals(0.0, testRange3.getCentralValue(), delta);
    }

}
