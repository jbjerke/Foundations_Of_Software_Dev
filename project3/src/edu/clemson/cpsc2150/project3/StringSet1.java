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
 * @correspondence_conceptual this = Union {contents(i)}
 *                      for i = 1 to next
 */

public class StringSet1 extends AbstractSSE {
    /*
    This class uses a java.util.List implementation, such that
    the unique elements of the set are stored in any order in
    the list

    correspondence conceptual this = Union {contents(i)}
                        for i = 1 to next
     */

    private List<String> se1;

    StringSet1 ( ){
        se1 = new LinkedList<>( );
    }

    @Override
    public void insert(String element) {
        if(isEmpty( )){
            se1.add(element);
        }
        else if(!contains(element)){
            se1.add(element);
        }
    }

    @Override
    public boolean contains(String element) {
        return se1.contains(element);
    }

    @Override
    public void remove(String element) {
        se1.remove(element);
    }

    @Override
    public String removeAny( ) {
        return se1.remove(0);
    }

    @Override
    public int size( ) {
        return se1.size( );
    }

    @Override
    public boolean isEmpty( ) {
        return size( ) == 0;
    }

    @Override
    public void clear(  ) {
        while(!isEmpty( )){
            removeAny( );
        }
    }
}
