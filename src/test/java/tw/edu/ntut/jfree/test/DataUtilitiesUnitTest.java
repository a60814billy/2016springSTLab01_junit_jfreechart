package tw.edu.ntut.jfree.test;

import org.jfree.data.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void testCalculateColumnTotalWithNull(){
        FakeValues2DNull val = new FakeValues2DNull();
        double result = DataUtilities.calculateColumnTotal(val, 0);
        assertEquals(0.0 , result, delta);
    }

//
//    @Test
//    public void testColumn2CalculateColumnTotal() {
//        double result = DataUtilities.calculateColumnTotal(this.testVal, 2);
//        assertEquals(24.0, result, delta);
//    }
//
//    @Test(expected = ArrayIndexOutOfBoundsException.class)
//    public void testOverColumnIndexCalculateColumnTotal() {
//        DataUtilities.calculateColumnTotal(this.testVal, 3);
//    }

//    @Test(expected = IllegalArgumentException.class)
//    public void testDataIsNullColumnIndexCalculateColumnTotal() {
//        this.testVal = null;
//        DataUtilities.calculateColumnTotal(this.testVal, 0);
//    }
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

//    @Test
//    public void testValidRowIsEmptyRowsCalculateColumnTotal() {
//        double result = DataUtilities.calculateColumnTotal(this.testVal, 0, new int[]{});
//        assertEquals(0.0, result, delta);
//    }
//
//    @Test
//    public void testValidRowIsNullRowsCalculateColumnTotal() {
//        boolean pass = false;
//        try {
//            DataUtilities.calculateColumnTotal(this.testVal, 0, null);
//            fail();
//        } catch (NullPointerException e) {
//            pass = true;
//        }
//        assertTrue(pass);
//
//    }

//    @Test(expected = IllegalArgumentException.class)
//    public void testDataIsNullValidRowsCalculateColumnTotal() {
//        double result = DataUtilities.calculateColumnTotal(null, 0, new int[]{});
//        assertEquals(0.0, result, delta);
//    }


    @Test
    public void testCalculateColumnTotalWithNull2(){
        FakeValues2DNull val = new FakeValues2DNull();
        double result = DataUtilities.calculateColumnTotal(val, 0, new int[]{1,2,10});
        assertEquals(0.0 , result, delta);
    }
    //endregion

    //region test public static double calculateRowTotal(Values2D data,int row)
    @Test
    public void testRow0CalculateRowTotal() {
        double result = DataUtilities.calculateRowTotal(this.testVal, 0);
        assertEquals(12.0, result, delta);
    }

    @Test
    public void testCalculateRowTotalWithNull() {
        Values2D val = new FakeValues2DNull();
        double result = DataUtilities.calculateRowTotal(val, 0);
        assertEquals(0.0, result, delta);
    }

//    @Test
//    public void testRow2CalculateRowTotal() {
//        double result = DataUtilities.calculateRowTotal(this.testVal, 2);
//        assertEquals(18.0, result, delta);
//    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRowOverIndexCalculateRowTotal() {
        double result = DataUtilities.calculateRowTotal(this.testVal, 3);
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void testDataNullOnCalculateRowTotal() {
//        this.testVal = null;
//        double result = DataUtilities.calculateRowTotal(this.testVal, 0);
//    }
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

//    @Test
//    public void testValidColIsEmptyCalculateRowTotal() {
//        double result = DataUtilities.calculateRowTotal(this.testVal, 1, new int[]{});
//        assertEquals(0.0, result, delta);
//    }

//    @Test(expected = IllegalArgumentException.class)
//    public void testDataIsNullValidCalculateRowTotal() {
//        this.testVal = null;
//        DataUtilities.calculateRowTotal(this.testVal, 1, new int[]{});
//    }

    @Test
    public void testCalculateRowTotalWithNull2() {
        Values2D val = new FakeValues2DNull();
        double result = DataUtilities.calculateRowTotal(val, 0, new int[]{1,2,12});
        assertEquals(0.0, result, delta);
    }

//    @Test
//    public void testValidColIsNullValidCalculateRowTotal() {
//        boolean pass = false;
//        try {
//            DataUtilities.calculateRowTotal(this.testVal, 1, null);
//            fail();
//        } catch (NullPointerException e) {
//            pass = true;
//        }
//        assertTrue(pass);
//    }
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
    public void testBasicGetCumulativePercentagesWithNull() {
        DefaultKeyedValues testData = new DefaultKeyedValues();
        String[] keys = {"1", "2", "3", "4", "5"};
        Double[] expected = {Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN};

        for (String key : keys) {
            testData.addValue(key, null);
        }
        KeyedValues result = DataUtilities.getCumulativePercentages(testData);
        for (int i = 0; i < keys.length; i++) {
            assertEquals(expected[i], (Double) result.getValue(keys[i]), delta);
        }
    }

//    @Test
//    public void testComplexGetCumulativePercentages() {
//        DefaultKeyedValues testData = new DefaultKeyedValues();
//        String[] keys = {"a", "b", "c", "d", "e"};
//        Integer[] values = {1, 1, 3, 1, 4};
//        Double[] expected = {.1, .2, .5, .6, 1.0};
//
//        for (int i = 0; i < keys.length; i++) {
//            testData.addValue(keys[i], values[i]);
//        }
//        KeyedValues result = DataUtilities.getCumulativePercentages(testData);
//        for (int i = 0; i < keys.length; i++) {
//            assertEquals(expected[i], (Double) result.getValue(keys[i]), delta);
//        }
//    }

//    @Test
//    public void testDataIsEmptyGetCumulativePercentages() {
//        DefaultKeyedValues testData = new DefaultKeyedValues();
//        KeyedValues result = DataUtilities.getCumulativePercentages(testData);
//        assertEquals(0, result.getItemCount());
//    }

//    @Test(expected = IllegalArgumentException.class)
//    public void testDataIsNullGetCumulativePercentages() {
//        KeyedValues result = DataUtilities.getCumulativePercentages(null);
//    }
    //endregion

    //region test public static java.lang.Number[] createNumberArray(double[] data)
//    @Test
//    public void testCreateNumberArray() {
//        Number[] result = DataUtilities.createNumberArray(new double[]{1.1, 2.2, 3.3});
//        Number[] expected = {1.1, 2.2, 3.3};
//        assertArrayEquals(expected, result);
//    }

//    @Test
//    public void testDataIsEmptyCreateNumberArray() {
//        Number[] result = DataUtilities.createNumberArray(new double[]{});
//        Number[] expected = {};
//        assertArrayEquals(expected, result);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testDataIsNullCreateNumberArray() {
//        DataUtilities.createNumberArray(null);
//    }
    //endregion

    //region test public static java.lang.Number[] createNumberArray2D(double[][] data)
    @Test
    public void testCreateNumberArray2D() {
        double[][] data = {{1.1, 2.2, 3.3}, {1.1, 2.2, 3.3}, {1.1, 2.2, 3.3}, {1.1, 2.2, 3.3}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        Number[][] expected = {{1.1, 2.2, 3.3}, {1.1, 2.2, 3.3}, {1.1, 2.2, 3.3}, {1.1, 2.2, 3.3}};
        assertEquals(4, result.length);
        assertEquals(3, result[0].length);
        assertArrayEquals(expected, result);
    }

//    @Test
//    public void testDataIsEmptyCreateNumberArray2D() {
//        Number[][] result = DataUtilities.createNumberArray2D(new double[][]{});
//        Number[] expected = {};
//        assertArrayEquals(expected, result);
//    }

//    @Test(expected = IllegalArgumentException.class)
//    public void testDataIsNullCreateNumberArray2D() {
//        DataUtilities.createNumberArray2D(null);
//    }
    //endregion

    //region test public static double[][] clone(double[][] source)
    @Test
    public void testClone() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertArrayEquals(data, data);
        double[][] result = DataUtilities.clone(data);
        assertEquals(data.length, result.length);
        for (int i = 0; i < data.length; i++) {
            assertEquals(data[i].length, result[i].length);
        }
        assertArrayEquals(data, result);
        assertFalse(result == data);
        data[0][0] = 0;
        assertNotEquals(data[0][0], result[0][0]);
    }

//    @Test
//    public void testDataIsEmptyClone() {
//        double[][] data = {};
//        assertArrayEquals(data, data);
//        double[][] result = DataUtilities.clone(data);
//        assertArrayEquals(data, result);
//        assertFalse(result == data);
//    }

//    @Test(expected = IllegalArgumentException.class)
//    public void testDataIsNullClone() {
//        DataUtilities.clone(null);
//    }
    //endregion

    //region test public static boolean equal(double[][] a, double[][] b)
    @Test
    public void testEqual() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertFalse(data == data2);
        assertArrayEquals(data, data2);
        assertTrue(DataUtilities.equal(data, data2));
    }

    @Test
    public void testDimensionNotSameEqual() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8}};
        assertFalse(data == data2);
        assertFalse(DataUtilities.equal(data, data2));
    }

    @Test
    public void testLengthNotSameEqual() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] data2 = {{1, 2, 3}, {4, 5, 6}};
        assertFalse(data == data2);
        assertFalse(DataUtilities.equal(data, data2));
    }

    @Test
    public void testTwoDataIsNullEqual() {
        assertTrue(DataUtilities.equal(null, null));
    }

    @Test
    public void testdata2IsNullEqual() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertFalse(DataUtilities.equal(data, null));
    }

//    @Test
//    public void testTwoData1IsNullEqual() {
//        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        assertFalse(DataUtilities.equal(null, data));
//    }
    //endregion

}
