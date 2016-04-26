package tw.edu.ntut.jfree.test.io;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.io.CSV;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class CSVTest {

    private final double delta = 0.001;

    /**
     * Test CVS show as below
     * \      , JFreeSVG, Batik
     * Warm-up,     7445, 24448
     * Test   ,     4297, 21022
     */
    @Test
    public void testBasicReadCategoryDataset() {
        String csvData = String.format("%s\n%s\n%s", ",\"JFreeSVG\",\"Batik\"", "\"Warm-up\",\"7445\",\"24448\"", "\"Test\",\"4297\",\"21022\"");
        StringReader sr = new StringReader(csvData);
        CSV csv = new CSV(',', '"');
        String[] rowKeys = {"Warm-up", "Test"};
        String[] colKeys = {"JFreeSVG", "Batik"};
        CategoryDataset result;
        try {
            result = csv.readCategoryDataset(sr);
            assertEquals(2, result.getRowCount());
            assertEquals(rowKeys[0], result.getRowKey(0));
            assertEquals(rowKeys[1], result.getRowKey(1));
            assertEquals(2, result.getColumnCount());
            assertEquals(colKeys[0], result.getColumnKey(0));
            assertEquals(colKeys[1], result.getColumnKey(1));
            assertEquals(7445.0, (Double) result.getValue(rowKeys[0], colKeys[0]), delta);
            assertEquals(24448.0, (Double) result.getValue(rowKeys[0], colKeys[1]), delta);
            assertEquals(4297.0, (Double) result.getValue(rowKeys[1], colKeys[0]), delta);
            assertEquals(21022.0, (Double) result.getValue(rowKeys[1], colKeys[1]), delta);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * Test CVS show as below
     * \      , JFreeSVG, Batik
     * Warm-up,     7445, 24448
     * Test   ,     4297, 21022
     */
    @Test
    public void testBasicReadCategoryDatasetWithtextDelimiterError() {
        String csvData = String.format("%s\n%s\n%s", ",\"JFreeSVG\",\"Batik\"", "\"Warm-up\",\"7445\",\"24448\"", "\"Test\",\"4297\",\"21022\"");
        StringReader sr = new StringReader(csvData);
        CSV csv = new CSV(',', '\'');
        String[] rowKeys = {"Warm-up", "Test"};
        String[] colKeys = {"JFreeSVG", "Batik"};
        CategoryDataset result;
        try {
            result = csv.readCategoryDataset(sr);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testUseAnotherFieldDelimiterReadCategoryDataset() {
        String csvData = String.format("%s\n%s\n%s", ".\"JFreeSVG\".\"Batik\"", "\"Warm-up\".\"7445\".\"24448\"", "\"Test\".\"4297\".\"21022\"");
        StringReader sr = new StringReader(csvData);
        CSV csv = new CSV('.', '"');
        String[] rowKeys = {"Warm-up", "Test"};
        String[] colKeys = {"JFreeSVG", "Batik"};
        CategoryDataset result;
        try {
            result = csv.readCategoryDataset(sr);
            assertEquals(2, result.getRowCount());
            assertEquals(rowKeys[0], result.getRowKey(0));
            assertEquals(rowKeys[1], result.getRowKey(1));
            assertEquals(2, result.getColumnCount());
            assertEquals(colKeys[0], result.getColumnKey(0));
            assertEquals(colKeys[1], result.getColumnKey(1));
            assertEquals(7445.0, (Double) result.getValue(rowKeys[0], colKeys[0]), delta);
            assertEquals(24448.0, (Double) result.getValue(rowKeys[0], colKeys[1]), delta);
            assertEquals(4297.0, (Double) result.getValue(rowKeys[1], colKeys[0]), delta);
            assertEquals(21022.0, (Double) result.getValue(rowKeys[1], colKeys[1]), delta);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testAnotherTextDelimiterReadCategoryDataset() {
        String csvData = String.format("%s\n%s\n%s", ",'JFreeSVG','Batik'", "'Warm-up','7445','24448'", "'Test','4297','21022'");
        StringReader sr = new StringReader(csvData);
        CSV csv = new CSV(',', '\'');
        String[] rowKeys = {"Warm-up", "Test"};
        String[] colKeys = {"JFreeSVG", "Batik"};
        CategoryDataset result;
        try {
            result = csv.readCategoryDataset(sr);
            assertEquals(2, result.getRowCount());
            assertEquals(rowKeys[0], result.getRowKey(0));
            assertEquals(rowKeys[1], result.getRowKey(1));
            assertEquals(2, result.getColumnCount());
            assertEquals(colKeys[0], result.getColumnKey(0));
            assertEquals(colKeys[1], result.getColumnKey(1));
            assertEquals(7445.0, (Double) result.getValue(rowKeys[0], colKeys[0]), delta);
            assertEquals(24448.0, (Double) result.getValue(rowKeys[0], colKeys[1]), delta);
            assertEquals(4297.0, (Double) result.getValue(rowKeys[1], colKeys[0]), delta);
            assertEquals(21022.0, (Double) result.getValue(rowKeys[1], colKeys[1]), delta);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testIOExceptionReadCategoryDataset() {
        CSV csv = new CSV();
        try {
            CategoryDataset result = csv.readCategoryDataset(new StringReader(""));
            assertEquals(0, result.getColumnCount());
            assertEquals(0, result.getRowCount());
        } catch (IOException e) {
            fail();
        }

    }

}
