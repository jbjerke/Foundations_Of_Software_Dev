package edu.clemson.cpsc2150.project3;

/*
 * Jordan Bjerken
 * Section 01
 * CPSC 2150 Project 3
 * 4/2/17
 */
/**
 * @invariant       for all s: String, Occurs_Ct(s, q) <= 1;
 * @correspondence_conceptual this = Entries(q);
 */

public class StringSet2 extends AbstractSSE {
    /*
    This class uses a StringQueueExtended implementation, such
    that the unique elements of the set are stored in any order
    in an instance of StringQueueExtended.

    invariant       for all s: String, Occurs_Ct(s, q) <= 1;
    correspondence conceptual this = Entries(q);
     */

    private StringQueueExtended se2;

    StringSet2( ){
        se2 = new StringQueue2( );
    }

    @Override
    public void insert(String element) {
        if(!isEmpty( )){
            se2.enqueue(element);
        }
        else if(!contains(element)){
            se2.enqueue(element);
        }
    }

    @Override
    public boolean contains(String element) {
        String temp;
        boolean fnd = false;
        for(int j = 0; j < se2.length( ); j++){
            temp = se2.dequeue( );

            if(temp.equals(element)){
                fnd = true;
            }

            se2.enqueue(temp);
        }

        return fnd;
    }

    @Override
    public void remove(String element) {
        String temp;
        while(contains(element)){
            temp = se2.dequeue( );
            if(!temp.equals(element)){
                se2.enqueue(temp);
            }
        }
    }

    @Override
    public String removeAny( ) {
        return se2.dequeue( );
    }

    @Override
    public int size( ) {
        return se2.length( );
    }

    @Override
    public boolean isEmpty( ) {
        return se2.isEmpty( );
    }

    @Override
    public void clear( ) {
        se2.clear( );
    }
}
