package tw.edu.ntut.jfree.test;

import org.jfree.data.Values2D;

class FakeValues2DNull implements Values2D {


    public int getRowCount() {
        return 5;
    }

    public int getColumnCount() {
        return 5;
    }

    public Number getValue(int i, int j) {
        return null;
    }
}
