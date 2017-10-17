package edu.clemson.cpsc2150.project2;

/*
 * Jordan Bjerken
 * Section 01
 * CPSC 2150 Project 2
 * 3/5/17
 */

import java.util.ArrayList;
import java.util.List;

public class MazeImpl2 implements Maze {
    /**
     * @invariant 0 <= |obs| <= 25
     */
    // Arrow to indicate rat chars
    private static final char[] DIRECTIONS = {
        /* DOWN */ (char) 8595,
        /* LEFT */ (char) 8592,
        /* UP */ (char) 8593,
        /* DOWN */ (char) 8594
    };

    // Variables
    private int[] rat; // rat[0] = row, rat[1] = column
    private int[] cheese; // cheese[0] = row, cheese[1] = column
    private int ratDirection; // 0 = DOWN, 1 = LEFT, 2 = UP, 3 = RIGHT
    private int mazeRow = 5, mazeColumn = 5;
    private List<Integer[]> obs;

    MazeImpl2( ) {
        obs = new ArrayList<>();
        rat = new int [2];
        cheese = new int [2];
        cheese[0] = -1;
        cheese[1] = -1;
        ratDirection = 0;
    }

    @Override
    public void placeRat(int row, int column) {
        // Do not place a rat on an obstacle
        if (hasObstacle(row,column)) {
            System.out.println("There is already an obstacle here.");
            return;
        }

        // Get the new coordinates for the rat
        rat[0] = row;
        rat[1] = column;

        // Determine if the rat was placed on the cheese
        if (hasCheese(rat[0], rat[1]))
            System.out.println("Congratulations! The rat found the cheese!!");
    }

    @Override
    public void turnRat(int direction) {
        // Update ratDirection
        ratDirection = direction;
    }

    @Override
    public void moveRat() {
        // Determine the new rat coordinates
        switch (ratDirection) {
            case 0:
                rat[0] = rat[0] + 1;
                break;
            case 1:
                rat[1] = rat[1] - 1;
                break;
            case 2:
                rat[0] = rat[0] - 1;
                break;
            case 3:
                rat[1] = rat[1] + 1;
                break;
            default:
                break;
        }

        // Determine if the rat found the cheese
        if (hasCheese(rat[0], rat[1])) {
            System.out.println("Congratulations! The rat found the cheese!!");
            cheese[0] = -1;
            cheese[1] = -1;
        }
    }

    @Override
    public boolean canRatMove() {
        boolean canIt = true;
        switch (ratDirection) {
            case 0:
                if (rat[0] == 4)
                    canIt = false;

                for (Integer[] i : obs){
                    if(i[0] == (rat[0] + 1) && i[1] == rat[1])
                        canIt = false;
                }
                break;

            case 1:
                if (rat[1] == 0)
                    canIt = false;

                for (Integer[] i : obs){
                    if(i[0] == rat[0] && i[1] == (rat[1]-1))
                        canIt = false;
                }
                break;

            case 2:
                if (rat[0] == 0)
                    canIt = false;
                for (Integer[] i : obs){
                    if(i[0] == (rat[0] - 1) && i[1] == rat[1])
                        canIt = false;
                }
                break;

            case 3:
                if (rat[1] == 4)
                    return false;
                for (Integer[] i : obs){
                    if(i[0] == rat[0] && i[1] == (rat[1]+1))
                        canIt = false;
                }
                break;
        }

        return canIt;
    }

    @Override
    public int getRatDirection() {
        return ratDirection;
    }

    @Override
    public void placeCheese(int row, int column) {
        // Do not place cheese on an obstacle
        if (hasObstacle(row,column)) {
            System.out.println("There is already an obstacle here.");
            return;
        }

        // Place the cheese
        cheese[0] = row;
        cheese[1] = column;

        // Determine if the rat found the cheese
        if (hasCheese(rat[0], rat[1])) {
            System.out.println("Congratulations! The rat found the cheese!!");
            cheese[0] = -1;
            cheese[1] = -1;
        }
    }

    @Override
    public void placeObstacle(int row, int column) {
        // Do not place an obstacle on an already existing obstacle
        for (Integer[] i : obs) {
            if (i[0] == row && i[1] == column) {
                System.out.println("Cannot place obstacle at " + row + " " + column + ".");
                return;
            }
        }

        // Create a new addition to the array
        obs.add(new Integer[2]);
        obs.get(obs.size() - 1)[0] = row;
        obs.get(obs.size() - 1)[1] = column;
    }

    @Override
    public void remove(int row, int column) {
        // Move the rat outside of the maze
        if(rat[0] == row && rat[1] == column) {
            rat[0] = -1;
            rat[1] = -1;
        }

        // Move the cheese outside of the maze
        if(cheese[0] == row && cheese[1] == column) {
            cheese[0] = -1;
            cheese[1] = -1;
        }

        int count = 0;
        for(Integer[] i : obs){
            if(i[0] == row && i[1] == column)
                obs.remove(count);

            count++;
        }
    }

    @Override
    public boolean hasRat(int row, int column) {
        return rat[0] == row && rat[1] == column;
    }

    @Override
    public boolean hasCheese(int row, int column) {
        return cheese[0] == row && cheese[1] == column;
    }

    @Override
    public boolean hasObstacle(int row, int column) {
        for(Integer[] i : obs){
            if(i[0] == row && i[1] == column)
                return true;
        }

        return false;
    }

    @Override
    public void display() {
        int m, n;
        char printMe = ' ';
        for(n = 0; n < mazeRow + 2; n++){
            for(m = 0; m < mazeColumn + 2; m++){
                    if (n == 0 || n == mazeRow + 1 || m == 0 || m == mazeColumn + 1)
                        System.out.print("X ");
                    else {
                    for(Integer[] i : obs){
                        if(i[0] == (n - 1) && i[1] == (m-1)) {
                            printMe = 'X';
                        }
                    }

                    if(rat[0] == (n-1) && rat[1] == (m-1))
                        printMe = DIRECTIONS[ratDirection];
                    else if(cheese[0] == (n-1) && cheese[1] == (m-1))
                        printMe = 'C';

                    System.out.print(printMe + " ");

                    printMe = ' ';
                }
            }
            System.out.println();
        }
    }
}
