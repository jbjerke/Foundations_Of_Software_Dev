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
 * @correspondence_conceptual this = Reverse(contents);
*/

public class StringQueue2 extends AbstractSQE {
    /*
    This class uses a java.util.List implementation, such that
    the front of the queue is always located at the last index of the list.

    correspondence conceptual this = Reverse(contents);
     */

    private List<String> qe2;
    StringQueue2( ){
        qe2 = new LinkedList<>( );
    }

    @Override
    public void enqueue(String element) {
        qe2.add(0, element);
    }

    @Override
    public String dequeue( ) {
        return qe2.remove(length( )-1);
    }

    @Override
    public String front( ) {
        return qe2.get(length( )-1);
    }

    @Override
    public int length( ) {
        return qe2.size( );
    }

    @Override
    public boolean isEmpty( ) {
        return length( ) == 0;
    }

    @Override
    public void clear( ) {
        while(!isEmpty( )){
            dequeue( );
        }
    }
}
