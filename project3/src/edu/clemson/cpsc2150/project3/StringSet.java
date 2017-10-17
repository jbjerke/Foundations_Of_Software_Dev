package edu.clemson.cpsc2150.project3;

/*
 * Jordan Bjerken
 * Section 01
 * CPSC 2150 Project 3
 * 4/2/17
 */

/**
 * @mathematical_model set(Z);
 * @initialization_ensures this = {};
 */

public interface StringSet {

    /**
     * @param element The element to be inserted into the set
     *
     * @updates this;
     * @requires element is not in this;
     * @ensures this = {element} union #this;
     */
    void insert(String element);

    /**
     * @param element The element to be searched for in the set.
     * @return true if (element is_in this), false otherwise.
     *
     * @ensures this = #this;
     */
    boolean contains(String element);

    /**
     * @param element The element to be removed from the set.
     *
     * @updates this;
     * @requires |#this| > 0 and element is_in #this;
     * @ensures this = #this union {remove} and |#this| = |this| + 1;
     */
    void remove(String element);

    /**
     * @return The element at the front of the set.
     *
     * @updates this;
     * @requires |this| > 0;
     * @ensures |#this| = |this| + 1 and #this = {remove} union this;
     */
    String removeAny( );

    /**
     * @return |this|.
     *
     * @requires |this| >= 0;
     * @ensures this = #this;
     */
    int size( );

    /**
     * @return true if |this| = 0, false otherwise.
     *
     * @ensures this = #this;
     */
    boolean isEmpty( );

    /**
     * @updates this;
     * @requires |this| > 0;
     * @ensures |this| = 0 and |#this| = union{removes};
     */
    void clear( );
}
