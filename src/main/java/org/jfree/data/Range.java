/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2014, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * ----------
 * Range.java
 * ----------
 * (C) Copyright 2002-2014, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Chuanhao Chiu;
 *                   Bill Kelemen;
 *                   Nicolas Brodu;
 *                   Sergei Ivanov;
 *
 * Changes (from 23-Jun-2001)
 * --------------------------
 * 22-Apr-2002 : Version 1, loosely based by code by Bill Kelemen (DG);
 * 30-Apr-2002 : Added getLength() and getCentralValue() methods.  Changed
 *               argument check in constructor (DG);
 * 13-Jun-2002 : Added contains(double) method (DG);
 * 22-Aug-2002 : Added fix to combine method where both ranges are null, thanks
 *               to Chuanhao Chiu for reporting and fixing this (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 14-Aug-2003 : Added equals() method (DG);
 * 27-Aug-2003 : Added toString() method (BK);
 * 11-Sep-2003 : Added Clone Support (NB);
 * 23-Sep-2003 : Fixed Checkstyle issues (DG);
 * 25-Sep-2003 : Oops, Range immutable, clone not necessary (NB);
 * 05-May-2004 : Added constrain() and intersects() methods (DG);
 * 18-May-2004 : Added expand() method (DG);
 * ------------- JFreeChart 1.0.x ---------------------------------------------
 * 11-Jan-2006 : Added new method expandToInclude(Range, double) (DG);
 * 18-Dec-2007 : New methods intersects(Range) and scale(...) thanks to Sergei
 *               Ivanov (DG);
 * 08-Jan-2012 : New method combineIgnoringNaN() (DG);
 * 23-Feb-2014 : Added isNaNRange() method (DG);
 * 
 */

package org.jfree.data;

import java.io.Serializable;
import org.jfree.chart.util.ParamChecks;

/**
 * Represents an immutable range of values.
 */
public strictfp class Range implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -906333695431863380L;

    /** The lower bound of the range. */
    private double lower;

    /** The upper bound of the range. */
    private double upper;

    /**
     * Creates a new range.
     *
     * @param lower  the lower bound (must be &lt;= upper bound).
     * @param upper  the upper bound (must be &gt;= lower bound).
     */
    public Range(double lower, double upper) {
        if (lower > upper) {
            String msg = "Range(double, double): require lower (" + lower
                + ") <= upper (" + upper + ").";
            throw new IllegalArgumentException(msg);
        }
        this.lower = lower;
        this.upper = upper;
    }

    /**
     * Returns the lower bound for the range.
     *
     * @return The lower bound.
     */
    public double getLowerBound() {
        return this.lower;
    }

    /**
     * Returns the upper bound for the range.
     *
     * @return The upper bound.
     */
    public double getUpperBound() {
        return this.upper;
    }

    /**
     * Returns the length of the range.
     *
     * @return The length.
     */
    public double getLength() {
        return this.upper - this.lower;
    }

    /**
     * Returns the central value for the range.
     *
     * @return The central value.
     */
    public double getCentralValue() {
        return this.lower / 2.0 + this.upper / 2.0;
    }

    /**
     * Returns <code>true</code> if the range contains the specified value and
     * <code>false</code> otherwise.
     *
     * @param value  the value to lookup.
     *
     * @return <code>true</code> if the range contains the specified value.
     */
    public boolean contains(double value) {
        return (value >= this.lower && value <= this.upper);
    }

    /**
     * Returns <code>true</code> if the range intersects with the specified
     * range, and <code>false</code> otherwise.
     *
     * @param b0  the lower bound (should be &lt;= b1).
     * @param b1  the upper bound (should be &gt;= b0).
     *
     * @return A boolean.
     */
    public boolean intersects(double b0, double b1) {
        if (b0 <= this.lower) {
            return (b1 > this.lower);
        }
        else {
            return (b0 < this.upper && b1 >= b0);
        }
    }

    /**
     * Returns <code>true</code> if the range intersects with the specified
     * range, and <code>false</code> otherwise.
     *
     * @param range  another range (<code>null</code> not permitted).
     *
     * @return A boolean.
     *
     * @since 1.0.9
     */
    public boolean intersects(Range range) {
        return intersects(range.getLowerBound(), range.getUpperBound());
    }

    /**
     * Returns the value within the range that is closest to the specified
     * value.
     *
     * @param value  the value.
     *
     * @return The constrained value.
     */
    public double constrain(double value) {
        double result = value;
        if (!contains(value)) {
            if (value > this.upper) {
                result = this.upper;
            }
            else if (value < this.lower) {
                result = this.lower;
            }
        }
        return result;
    }

}
