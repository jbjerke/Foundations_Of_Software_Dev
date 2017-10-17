package edu.clemson.cpsc2150.project3;

/*
 * Jordan Bjerken
 * Section 01
 * CPSC 2150 Project 3
 * 4/2/17
 */

import java.util.LinkedList;
import java.util.List;

/**
 * @correspondence_conceptual this = contents;
 */

public class StringQueue1 extends AbstractSQE {
    /*
    This class uses a java.util.List implementation, such that
    the front of the queue is always located at the first index
    of the list.

    correspondence conceptual this = contents;
    */

    private List<String> qe1;

    // Constructor
    StringQueue1 ( ){
        qe1 = new LinkedList<>();
    }

    @Override
    public void enqueue(String element) {
        qe1.add(element);
    }

    @Override
    public String dequeue() {
        return qe1.remove(0);
    }

    @Override
    public String front( ) {
        return qe1.get(0);
    }

    @Override
    public int length( ) {
        return qe1.size( );
    }

    @Override
    public boolean isEmpty( ) {
        return length( ) == 0;
    }

    @Override
    public void clear() {
        while(!isEmpty( )){
            dequeue( );
        }
    }
}
