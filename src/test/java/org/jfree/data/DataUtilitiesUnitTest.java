package org.jfree.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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

    //region test public static double calculateColumnTotal(Values2D data,int column, int[] validRows)
    @Test
    public void testValidRow0_2RowsCalculateColumnTotal() {
        double result = DataUtilities.calculateColumnTotal(this.testVal, 0, new int[]{0, 2});
        assertEquals(4.0, result, delta);
    }

    @Test
    public void testValidRow0RowsCalculateColumnTotal() {
        double result = DataUtilities.calculateColumnTotal(this.testVal, 0, new int[]{0});
        assertEquals(1.0, result, delta);
    }

    @Test
    public void testValidRowIsEmptyRowsCalculateColumnTotal() {
        double result = DataUtilities.calculateColumnTotal(this.testVal, 0, new int[]{});
        assertEquals(0.0, result, delta);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testValidRowIsNullRowsCalculateColumnTotal() {
        double result = DataUtilities.calculateColumnTotal(this.testVal, 0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataIsNullValidRowsCalculateColumnTotal() {
        double result = DataUtilities.calculateColumnTotal(null, 0, new int[]{});
        assertEquals(0.0, result, delta);
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

    //region test public static double calculateRowTotal(Values2D data,int row, int[] validCols)
    @Test
    public void testValidCol0_2CalculateRowTotal() {
        double result = DataUtilities.calculateRowTotal(this.testVal, 1, new int[]{0, 2});
        assertEquals(10.0, result, delta);
    }

    @Test
    public void testValidCol0CalculateRowTotal() {
        double result = DataUtilities.calculateRowTotal(this.testVal, 1, new int[]{0});
        assertEquals(2.0, result, delta);
    }

    @Test
    public void testValidColIsEmptyCalculateRowTotal() {
        double result = DataUtilities.calculateRowTotal(this.testVal, 1, new int[]{});
        assertEquals(0.0, result, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataIsNullValidCalculateRowTotal() {
        this.testVal = null;
        DataUtilities.calculateRowTotal(this.testVal, 1, new int[]{});
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testValidColIsNullValidCalculateRowTotal() {
        DataUtilities.calculateRowTotal(this.testVal, 1, null);
    }
    //endregion

    //region test public static KeyedValues getCumulativePercentages(KeyedValues data)
    @Test
    public void testBasicGetCumulativePercentages() {
        DefaultKeyedValues testData = new DefaultKeyedValues();
        String[] keys = {"1", "2", "3", "4", "5"};
        Double[] expected = {0.2, 0.4, 0.6, 0.8, 1.0};

        for (String key : keys) {
            testData.addValue(key, 1);
        }
        KeyedValues result = DataUtilities.getCumulativePercentages(testData);
        for (int i = 0; i < keys.length; i++) {
            assertEquals(expected[i], (Double) result.getValue(keys[i]), delta);
        }
    }

    @Test
    public void testComplexGetCumulativePercentages() {
        DefaultKeyedValues testData = new DefaultKeyedValues();
        String[] keys = {"a", "b", "c", "d", "e"};
        Integer[] values = {1, 1, 3, 1, 4};
        Double[] expected = {.1, .2, .5, .6, 1.0};

        for (int i = 0; i < keys.length; i++) {
            testData.addValue(keys[i], values[i]);
        }
        KeyedValues result = DataUtilities.getCumulativePercentages(testData);
        for (int i = 0; i < keys.length; i++) {
            assertEquals(expected[i], (Double) result.getValue(keys[i]), delta);
        }
    }

    @Test
    public void testDataIsEmptyGetCumulativePercentages() {
        DefaultKeyedValues testData = new DefaultKeyedValues();
        KeyedValues result = DataUtilities.getCumulativePercentages(testData);
        assertEquals(0, result.getItemCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataIsNullGetCumulativePercentages() {
        KeyedValues result = DataUtilities.getCumulativePercentages(null);
    }
    //endregion

}
