package edu.clemson.cpsc2150.project3;

/*
 * Jordan Bjerken
 * Section 01
 * CPSC 2150 Project 3
 * 4/2/17
 */

public abstract class AbstractSQE implements StringQueueExtended {

    @Override
    public final void invert( ){
        String[] temp = new String[length( )];

        for(int n = 0; n < length( ); n++){
            temp[n] = dequeue( );
        }

        for(int n = (length( ) - 1); n >= 0; n--){
            enqueue(temp[n]);
        }
    }

    @Override
    public final void mergeWith(StringQueue queue){
        queue.enqueue(dequeue( ));
    }

    @Override
    public final boolean equals(Object obj){
        // first make sure obj is not null
        // make sure obj is the same instance of
        return false;
    }

    /**
     * this method returns a string representation of the queue,
     * listing the elements from front to back
     * Example: [ "front", "elem2", "elem3", "back" ]
     */
    @Override
    public final String toString( ){
        String temp;
        String str = "[ \"";

        for(int i = 0; i < length( ); i++){
            if( i == length( ) -1){
                temp = dequeue( );
                str = str + temp + "\" ]";
                enqueue(temp);
            }
            else{
                temp = dequeue( );
                str = str + temp + "\", \"";
                enqueue(temp);
            }
        }
        return str;
    }
}
