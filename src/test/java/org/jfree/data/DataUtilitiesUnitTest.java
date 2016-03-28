package org.jfree.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataUtilitiesUnitTest {

    private FakeValues2D testVal;
    private final double delta = 0.0001;

    @Before
    public void setUp() {
        this.testVal = new FakeValues2D();
    }

    @After
    public void tearDown() {
        this.testVal = null;
    }

    //region test public static double calculateColumnTotal(Values2D data,int column)
    @Test
    public void testColumn0CalculateColumnTotal() {
        double result = DataUtilities.calculateColumnTotal(this.testVal, 0);
        assertEquals(6.0, result, delta);
    }

    @Test
    public void testColumn2CalculateColumnTotal() {
        double result = DataUtilities.calculateColumnTotal(this.testVal, 2);
        assertEquals(24.0, result, delta);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testOverColumnIndexCalculateColumnTotal() {
        DataUtilities.calculateColumnTotal(this.testVal, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataIsNullColumnIndexCalculateColumnTotal() {
        this.testVal = null;
        DataUtilities.calculateColumnTotal(this.testVal, 3);
    }
    //endregion

    //region test public static double calculateRowTotal(Values2D data,int row)
    @Test
    public void testRow0CalculateRowTotal() {
        double result = DataUtilities.calculateRowTotal(this.testVal, 0);
        assertEquals(12.0, result, delta);
    }

    @Test
    public void testRow2CalculateRowTotal() {
        double result = DataUtilities.calculateRowTotal(this.testVal, 2);
        assertEquals(18.0, result, delta);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRowOverIndexCalculateRowTotal() {
        double result = DataUtilities.calculateRowTotal(this.testVal, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataNullOnCalculateRowTotal() {
        this.testVal = null;
        double result = DataUtilities.calculateRowTotal(this.testVal, 0);
    }
    //endregion


}
