package edu.clemson.cpsc2150.project3;

/*
 * Jordan Bjerken
 * Section 01
 * CPSC 2150 Project 3
 * 4/2/17
 */

public abstract class AbstractSSE implements StringSetExtended {

    @Override
    public final void unionWith(StringSet rhs) {

        String temp;
        while(!this.isEmpty( )){
            temp = this.removeAny( );

            if(!rhs.contains(temp)){
                rhs.insert(temp);
            }
        }
    }

    @Override
    public final void intersectWith(StringSet rhs){
        String[] temp = new String[rhs.size()];
        String temp2;
        int count = 0;

        while(!this.isEmpty( )){
            temp2 = this.removeAny( );

            if(rhs.contains(temp2)){
                temp[count] = temp2;
                count++;
            }
        }

        rhs.clear( );

        for(int t = 0; t < count; t++){
            rhs.insert(temp[t]);
        }
    }

    @Override
    public final void differWith(StringSet rhs){
        String temp;

        while(!rhs.isEmpty( )){
            temp = rhs.removeAny( );
            if(contains(temp)){
                remove(temp);
            }
        }

        rhs.clear( );

        while(!isEmpty( )){
            temp = removeAny( );
            rhs.insert(temp);
        }
    }

    @Override
    public final boolean equals(Object obj){
        boolean eq = false, eq2 = true;

        if (obj == null) {
            eq = false;
        }
        else if (obj instanceof StringSet && ((StringSet) obj).size( ) == this.size( )){
            int count = 0;

            String[] temp1 = new String[this.size( )];
            String[] temp2 = new String[((StringSet) obj).size( )];

            while (!isEmpty( )){
                temp1[count] = removeAny( );
                temp2[count] = ((StringSet) obj).removeAny( );

                if (temp1[count].equals(temp2[count])){
                    eq = true;
                }
                else {
                    eq2 = false;
                }
            }

            //System.out.println(eq2);
            if (!eq2){
                eq = false;
            }

            for (String s : temp1){
                insert(s);
            }

            for (String st : temp2){
                ((StringSet) obj).insert(st);
            }
        }
        else{
            eq = false;
        }

        return eq;
    }

    /**
     * this method returns a string representation of the queue,
     * listing the elements from front to back
     * Example: { "front", "elem2", "elem3", "back" }
     */
    @Override
    public final String toString( ) {
        String[ ] temp = new String[size( )];
        String str = "{ \"";
        int s = size( );
        for(int j = 0; j < s; j++){
            if(j == (s - 1)){
                temp[j] = removeAny( );
                str = str + temp[j] + "\" }";
            }
            else{
                temp[j] = removeAny( );
                str = str + temp[j] + "\", \"";
            }
        }

        for(int m = 0; m < s; m++){
            insert(temp[m]);
        }

        return str;
    }
}
