package edu.clemson.cpsc2150.project3;

/*
 * Jordan Bjerken
 * Section 01
 * CPSC 2150 Project 3
 * 4/2/17
 */

public interface StringQueue {

    void enqueue(String element);

    String dequeue( );

    String front( );

    int length( );

    boolean isEmpty( );

    void clear( );
}
