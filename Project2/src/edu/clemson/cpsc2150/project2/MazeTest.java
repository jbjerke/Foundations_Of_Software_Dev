package edu.clemson.cpsc2150.project2;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Jordan Bjerken
 * Section 01
 * CPSC 2150 Project 2
 * 3/5/17
 */

class MazeTest {

    // Set up some variables
    Maze mtest;

    @org.junit.jupiter.api.BeforeEach
        void setUp() {
        // Instantiate mtest
        mtest = new MazeImpl2();

        // Create a maze with obstacles
        mtest.placeObstacle(0,1);
        mtest.placeObstacle(1,1);
        mtest.placeObstacle(3,1);
        mtest.placeObstacle(4,1);
        mtest.placeObstacle(1, 2);
        mtest.placeObstacle(3,2);
        mtest.placeObstacle(0, 3);
        mtest.placeObstacle(1, 3);
        mtest.placeObstacle(3,3);
        mtest.placeObstacle(4,3);

        // Place some cheese
        mtest.placeCheese(4, 4);

        /* Current Display
        X X X X X X X
        X R X   X   X
        X   X X X   X
        X           X
        X   X X X   X
        X   X   X C X
        X X X X X X X
         */
    }

    @org.junit.jupiter.api.Test
    void turnRat() {
        mtest.turnRat(0);
        assertTrue(mtest.getRatDirection() == 0);
        assertFalse(mtest.getRatDirection() == 2);

        mtest.turnRat(1);
        assertTrue(mtest.getRatDirection() == 1);
        assertFalse(mtest.getRatDirection() == 0);

        mtest.turnRat(2);
        assertTrue(mtest.getRatDirection() == 2);
        assertFalse(mtest.getRatDirection() == 3);

        mtest.turnRat(3);
        assertTrue(mtest.getRatDirection() == 3);
        assertFalse(mtest.getRatDirection() == 1);
    }

    @org.junit.jupiter.api.Test
    void canRatMove() {
        // Test moving through the array and trying to move in each direction after moving
        mtest.turnRat(1);
        assertFalse(mtest.canRatMove());

        mtest.turnRat(2);
        assertFalse(mtest.canRatMove());

        mtest.turnRat(3);
        assertFalse(mtest.canRatMove());

        mtest.turnRat(0);
        assertTrue(mtest.canRatMove());

        mtest.moveRat();
        mtest.turnRat(1);
        assertFalse(mtest.canRatMove());

        mtest.turnRat(2);
        assertTrue(mtest.canRatMove());

        mtest.turnRat(3);
        assertFalse(mtest.canRatMove());

        mtest.turnRat(0);
        assertTrue(mtest.canRatMove());

        mtest.moveRat();
        mtest.turnRat(1);
        assertFalse(mtest.canRatMove());

        mtest.turnRat(2);
        assertTrue(mtest.canRatMove());
        mtest.turnRat(0);
        assertTrue(mtest.canRatMove());

        mtest.turnRat(3);
        assertTrue(mtest.canRatMove());

        mtest.moveRat();
        mtest.turnRat(1);
        assertTrue(mtest.canRatMove());

        mtest.turnRat(2);
        assertFalse(mtest.canRatMove());

        mtest.turnRat(3);
        assertTrue(mtest.canRatMove());

        mtest.turnRat(0);
        assertFalse(mtest.canRatMove());

        // Make sure the rat will not move through the other two boundaries
        mtest.placeRat(0,4);
        mtest.turnRat(3);
        assertFalse(mtest.canRatMove());
        mtest.turnRat(2);
        assertFalse(mtest.canRatMove());

        mtest.placeRat(4,4);
        mtest.turnRat(0);
        assertFalse(mtest.canRatMove());
        mtest.turnRat(3);
        assertFalse(mtest.canRatMove());
    }

    @org.junit.jupiter.api.Test
    void placeObstacle() {
        assertTrue(mtest.hasObstacle(0,1));
        assertTrue(mtest.hasObstacle(3,1));
        assertTrue(mtest.hasObstacle(3,2));
        assertTrue(mtest.hasObstacle(1,3));
        assertTrue(mtest.hasObstacle(4,3));

        assertFalse(mtest.hasObstacle(4,0));
        assertFalse(mtest.hasObstacle(2,2));
        assertFalse(mtest.hasObstacle(0,2));

    }

    @org.junit.jupiter.api.Test
    void hasCheese() {
        assertTrue(mtest.hasCheese(4,4));
        mtest.remove(4,4);
        assertFalse(mtest.hasCheese(4,4));


        assertFalse(mtest.hasCheese(0,0));
        mtest.placeCheese(0,0);
        assertFalse(mtest.hasCheese(0,0)); // Rat is at [0,0], rat eats cheese, cheese gone

        mtest.placeCheese(0,2);
        assertTrue(mtest.hasCheese(0,2));
        assertFalse(mtest.hasCheese(0,0));
        mtest.remove(0,2);
        assertFalse(mtest.hasCheese(0,2));

        mtest.placeCheese(1, 0);
        assertTrue(mtest.hasCheese(1, 0));
        mtest.moveRat();
        assertFalse(mtest.hasCheese(1, 0));

    }

}