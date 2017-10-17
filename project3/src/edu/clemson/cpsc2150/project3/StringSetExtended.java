package edu.clemson.cpsc2150.project3;

/*
 * Jordan Bjerken
 * Section 01
 * CPSC 2150 Project 3
 * 4/2/17
 */

public interface StringSetExtended extends StringSet {

    /**
     * @param rhs The right-hand-side of the union operation
     * @clears this
     * @ensures
     * rhs = #this union #rhs
     */
    void unionWith(StringSet rhs);

    /**
     * @param rhs The right-hand-side of the intersect operation
     * @clears this
     * @ensures
     * rhs = #this intersect #rhs
     */
    void intersectWith(StringSet rhs);

    /**
     * @param rhs The right-hand-side of the intersect operation
     * @clears this
     * @ensures
     * rhs = #this \ #rhs
     */
    void differWith(StringSet rhs);
}
