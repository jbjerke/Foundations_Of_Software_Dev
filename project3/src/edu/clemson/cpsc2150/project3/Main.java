package edu.clemson.cpsc2150.project3;

/*
 * Jordan Bjerken
 * Section 01
 * CPSC 2150 Project 3
 * 4/2/17
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Check for proper number of command-line arguments.
        if(args.length != 1)
        dieWithError("Invalid number of command-line arguments: " + args.length + ". Should be 1.");
        // Attempt to open the file.
        File file = new File(args[0]);
        Scanner fileScanner = null;
        try { fileScanner = new Scanner(file); }
        catch (FileNotFoundException e) {
            dieWithError("Unable to find/open the file at the specified path: " + args[0] + ".");
        }

        // Parse the file in sets of four lines...
        StringSetExtended[] sets = { new StringSet1(), new StringSet2(), new StringSet1(), new StringSet2() };
        for(int n = 1; fileScanner.hasNext(); ++n) {
            System.out.println("// ~~~ Input Bundle #" + n + " ~~~ //");
            System.out.println("Input:");
            System.out.println( );
            // For each set...
            for(StringSet set : sets) {
                set.clear();
                // Get a new line from the file.
                String line = null;
                try { line = fileScanner.nextLine(); }
                catch (NoSuchElementException e) {
                    dieWithError("The number of lines in the file is not divisible by four.");
                }
                // Add each word in the line to the set.
                System.out.println(line);
                for(String word : line.split("\\s+"))
                    set.insert(word);
            }
            System.out.println();

            System.out.println("Output:");
            System.out.println();

            sets[0].unionWith(sets[1]);
            System.out.println("Union: " + sets[1]);
            sets[2].intersectWith(sets[3]);
            System.out.println("Intersection: " + sets[3]);
            sets[1].differWith(sets[3]);
            System.out.println("Difference: " + sets[3]);
            System.out.println("// ~~~ End Bundle ~~~ //\n");
        }
    }

    private static void dieWithError(String errorMessage) {
        System.err.println(errorMessage);
        System.err.println("Cannot recover, exiting.");
        System.exit(1);
    }
}
