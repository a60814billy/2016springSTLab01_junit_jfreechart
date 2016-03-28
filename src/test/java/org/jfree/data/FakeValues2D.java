package org.jfree.data;

import org.jfree.data.Values2D;

class FakeValues2D implements Values2D {

    private int data[][] = {
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9}
    };

    public int getRowCount() {
        return data.length;
    }

    public int getColumnCount() {
        return data[0].length;
    }

    public Number getValue(int i, int j) {
        return data[i][j];
    }
}
