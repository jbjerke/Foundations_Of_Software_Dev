package edu.clemson.cpsc2150.project3;

/*
 * Jordan Bjerken
 * Section 01
 * CPSC 2150 Project 3
 * 4/2/17
 */

public interface StringQueueExtended extends StringQueue {

    /**
     * @ensures
     * this = Reverse(#this)
     */
    void invert( );

    /**
     * @param queue The queue to merge with.
     * @clears this
     * @ensures
     * queue = #queue o #this
     */
    void mergeWith(StringQueue queue);
}
