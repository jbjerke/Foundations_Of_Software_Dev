package edu.clemson.cpsc2150.project2;

/*
 * Jordan Bjerken
 * Section 01
 * CPSC 2150 Project 2
 * 3/5/17
 */

/**
 * @mathematical_model Str(Z);
 * @initialization_ensures this = <rat>;
 * @defines maxSize : N;
 * @constraints |this| <= maxSize;
 */

public interface Maze {
    /**
     *@param row Row for the rat to be placed in.
     *@param column Column for the rat to be placed in.
     *
     *@update this
     *@requires [#this != <obstacle>] and [r*c <= |this|]
     *@ensures this = #this o <rat>
     */
    void placeRat (int row, int column);

    /**
     *@param direction The new direction the rat needs to face
     *
     * @update this && ratDirection
     * @requires [0 <= direction <= 3]
     * @ensures [ratDirection = param] and [Edit the display]
     */
    void turnRat (int direction);

    /**
     * @update this
     * @requires [this != #this o <rat>] and [<rat> != <obs>]
     * @ensures [rat moves 1 position]
     */
    void moveRat ( );

    /**
     * @return Indicator of whether the rat can move
     * @requires [this = #this]
     * and [Returns true if Rat's next position is not out of the maze or an obstacle]
     */
    boolean canRatMove( );

    /**
     * @return Integer indicating rat's current direction
     * @requires [ratDirection != null]
     * @ensures [ratDirection = #ratDirection]
     */
    int getRatDirection( );

    /**
     *@param row Row for the cheese to be placed in.
     *@param column Column for the cheese to be placed in.
     *
     *@updates this
     *
     * @requires [there is no cheese in the maze] and [this != X at location]
     * and [row and column are in the bounds of the maze]
     * @ensures [this = #this o <cheese>] and [maze contains only one cheese]
     */
    void placeCheese (int row, int column);

    /**
     *@param row Row for the obstacle to be placed in
     *@param column Column for the obstacle to be placed in
     *
     *@updates this
     *
     * @requires [#this != X]
     * and [row and column are in the bounds of the maze]
     *@ensures [obstacle is placed at specified location]
     */
    void placeObstacle (int row, int column);

/**
 *@param row Row for the item being removed
 *@param column Column for the item being removed
 *
 *@updates this
 * @requires [#this != " "]
 * and [row and column are in the bounds of the maze]
 * @ensures [this == " "]
 */
    void remove (int row, int column);

    /**
     * @param row row where the rat is being checked for
     * @param column column where the rat is being checked for
     * @return Indicator of if there is at rat at the specified location
     * @requires row and column to be in the bounds of the maze
     * @ensures [rat unchanged] and [ratDirection unchanged]
     * and [Returns true if and only if there is a rat at the specified location]
     */
    boolean hasRat (int row, int column);

    /**
     * @param row row where the cheese is being checked for
     * @param column column where the cheese is being checked for
     * @return Indicator of if there is cheese at the specified location
     * @requires row and column to be in the bounds of the maze
     * @ensures this = #this
     * and [Returns true if and only if there is a 'C' at the specified location]
     */
    boolean hasCheese (int row, int column);

    /**
     * @param row row where the obstacle is being checked for
     * @param column column where the obstacle is being checked for
     * @return Indicator of if there is obstacle at the specified location
     * @requires row and column to be in the bounds of the maze
     * @ensures this = #this
     * and [Returns true if and only if there is a 'X' at the specified location]
     */
    boolean hasObstacle (int row, int column);

    /**
     * @requires [maze != null]
     * @ensures [This method prints a visual representation of the maze]
     */
    void display ( );
}
