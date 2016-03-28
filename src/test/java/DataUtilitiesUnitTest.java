import org.jfree.data.DataUtilities;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataUtilitiesUnitTest {

    //region test public static double calculateColumnTotal(Values2D data,int column)
    @Test
    public void testColumn0CalculateColumnTotal() {
        FakeValues2D testVal = new FakeValues2D();
        double result = DataUtilities.calculateColumnTotal(testVal, 0);
        assertEquals(6.0, result, 0.0001);
    }

    @Test
    public void testColumn2CalculateColumnTotal() {
        FakeValues2D testVal = new FakeValues2D();
        double result = DataUtilities.calculateColumnTotal(testVal, 2);
        assertEquals(24.0, result, 0.0001);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testOverColumnIndexCalculateColumnTotal() {
        FakeValues2D testVal = new FakeValues2D();
        double result = DataUtilities.calculateColumnTotal(testVal, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataIsNullColumnIndexCalculateColumnTotal() {
        FakeValues2D testVal = null;
        double result = DataUtilities.calculateColumnTotal(testVal, 3);
    }
    //endregion

    //region test public static double calculateRowTotal(Values2D data,int row)
    @Test
    public void testRow0CalculateRowTotal() {
        FakeValues2D testVal = new FakeValues2D();
        double result = DataUtilities.calculateRowTotal(testVal, 0);
        assertEquals(12.0, result, 0.0001);
    }

    @Test
    public void testRow2CalculateRowTotal() {
        FakeValues2D testVal = new FakeValues2D();
        double result = DataUtilities.calculateRowTotal(testVal, 2);
        assertEquals(18.0, result, 0.0001);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRowOverIndexCalculateRowTotal() {
        FakeValues2D testVal = new FakeValues2D();
        double result = DataUtilities.calculateRowTotal(testVal, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataNullOnCalculateRowTotal() {
        FakeValues2D testVal = null;
        double result = DataUtilities.calculateRowTotal(testVal, 0);
    }
    //endregion


}
