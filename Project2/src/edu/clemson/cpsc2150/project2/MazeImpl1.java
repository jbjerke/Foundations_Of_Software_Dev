package edu.clemson.cpsc2150.project2;

/**
 * Jordan Bjerken
 * Section 01
 * CPSC 2150 Project 2
 * 3/5/17
 */

public class MazeImpl1 implements Maze {

    // Arrow to indicate rat
    private static final char[] DIRECTIONS = {
        /* DOWN */ (char) 8595,
        /* LEFT */ (char) 8592,
        /* UP */ (char) 8593,
        /* DOWN */ (char) 8594
    };

    // Variables
    private int[] rat; // rat[0] = row, rat[1] = column
    private int ratDirection; // 0 = DOWN, 1 = LEFT, 2 = UP, 3 = RIGHT
    private int mazeRow = 5, mazeColumn = 5;
    private char[][] maze = new char[mazeRow][mazeColumn];

    //Constructor
    MazeImpl1() {
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[x].length; y++) {
                maze[x][y] = ' ';
            }
        }
        rat = new int[2];
        ratDirection = 0;
        maze[rat[0]][rat[1]] = DIRECTIONS[ratDirection];
    }

    /**
     * @param row    Row for the rat to be placed in
     * @param column Column for the rat to be placed in
     * @requires [there is no rat in the maze] and [maze[row][column] != X]
     * and [row and column are in the bounds of the maze]
     * @ensures [rat is placed at specified location] and [direction is preserved]
     */
    public void placeRat(int row, int column) {
        // Do not place a rat on an obstacle
        if (hasObstacle(row,column)) {
            System.out.println("There is already an obstacle here.");
            return;
        }

        // Remove the rat from maze
        maze[rat[0]][rat[1]] = ' ';

        // Get the new coordinates of the rat
        rat[0] = row;
        rat[1] = column;

        // Determine if the rat was placed on the cheese
        if (hasCheese(rat[0], rat[1]))
            System.out.println("Congratulations! The rat found the cheese!!");

        // Update the position of the rat
        maze[rat[0]][rat[1]] = DIRECTIONS[ratDirection];
    }

    /**
     * @param direction The new direction the rat needs to face
     * @requires [direction != null] and [direction be 0, 1, 2, or 3]
     * @ensures [rat's new direction = param] and [Edit the display]
     */
    public void turnRat(int direction) {
        // Get the new direction
        ratDirection = direction;

        // Change the direction the arrow points
        maze[rat[0]][rat[1]] = DIRECTIONS[ratDirection];
    }

    /**
     * @requires [the rat not move out of maze] and [rat does not move on top of obstacle]
     * @ensures [rat moves 1 position]
     */
    public void moveRat() {
        // Remove the rat arrow from previous location
        remove(rat[0], rat[1]);

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
        if (hasCheese(rat[0], rat[1]))
            System.out.println("Congratulations! The rat found the cheese!!");

        // Place the rat in the new location
        maze[rat[0]][rat[1]] = DIRECTIONS[ratDirection];
    }

    /**
     * @return Indicator of whether the rat can move
     * @requires [ratDirection != null]
     * @ensures [ratDirection unchanged] and [rat does not move yet]
     * and [Returns true if Rat's next position is not out of the maze or an obstacle]
     */
    public boolean canRatMove() {
        switch (ratDirection) {
            case 0:
                if (rat[0] == 4)
                    return false;
                else if (maze[rat[0] + 1][rat[1]] == 'X')
                    return false;
                else
                    return true;
            case 1:
                if (rat[1] == 0)
                    return false;
                else if (maze[rat[0]][rat[1] - 1] == 'X')
                    return false;
                else
                    return true;
            case 2:
                if (rat[0] == 0)
                    return false;
                else if (maze[rat[0] - 1][rat[1]] == 'X')
                    return false;
                else
                    return true;
            case 3:
                if (rat[1] == 4)
                    return false;
                else if (maze[rat[0]][rat[1] + 1] == 'X')
                    return false;
                else
                    return true;
            default:
                return false;
        }
    }
    /** @return Integer indicating rat's current direction
     * @requires [ratDirection != null]
     * @ensures [ratDirection unchanged]
     */
    public int getRatDirection( ){
        return ratDirection;
    }

    /**
     *@param row Row for the cheese to be placed in
     *@param column Column for the cheese to be placed in
     * @requires [there is no cheese in the maze] and [maze[row][column] != X]
     * and [row and column are in the bounds of the maze]
     * @ensures [cheese is placed at specified location] and [maze contains only one cheese]
     */
    public void placeCheese (int row, int column){
        // Do not place cheese on an obstacle
        if (hasObstacle(row,column)) {
            System.out.println("There is already an obstacle here.");
            return;
        }

        // Remove the other cheese from maze
        for(int r = 0; r < mazeRow; r++){
            for(int q = 0; q < mazeColumn; q++){
                if(maze[r][q] == 'C')
                    maze[r][q] = ' ';
            }
        }

        // Place the cheese
        maze[row][column] = 'C';

        if (hasCheese(rat[0], rat[1])) {
            System.out.println("Congratulations! The rat found the cheese!!");
            placeRat(row,column);
        }
    }

    /**
     *@param row Row for the obstacle to be placed in
     *@param column Column for the obstacle to be placed in
     *@requires [maze[row][column] != X already]
     * and [row and column are in the bounds of the maze]
     *@ensures [obstacle is placed at specified location]
     */
    public void placeObstacle (int row, int column){
        // Do not place an obstacle on the rat or cheese
        if((rat[0]==row && rat[1]==column)||(hasCheese(row,column))){
            System.out.println("Cannot place obstacle at " + row + " " + column + ".");
            return;
        }

        // Place the obstacle
        maze[row][column] = 'X';
    }

    /**
     *@param row Row for the item being removed
     *@param column Column for the item being removed
     * @requires [maze[row][column] != " "]
     * and [row and column are in the bounds of the maze]
     * @ensures [maze[row][column] == " "]
     */
    public void remove (int row, int column){
        maze[row][column] = ' ';
    }

    /**
     * @param row row where the rat is being checked for
     * @param column column where the rat is being checked for
     * @return Indicator of if there is at rat at the specified location
     * @requires row and column to be in the bounds of the maze
     * @ensures [rat unchanged] and [ratDirection unchanged]
     * and [Returns true if and only if there is a rat at the specified location]
     */
    public boolean hasRat (int row, int column){
        return rat[0] == row && rat[1] == column;
    }

    /**
     * @param row row where the cheese is being checked for
     * @param column column where the cheese is being checked for
     * @return Indicator of if there is cheese at the specified location
     * @requires row and column to be in the bounds of the maze
     * @ensures [cheese unchanged]
     * and [Returns true if and only if there is a 'C' at the specified location]
     */
    public boolean hasCheese (int row, int column){
        return maze[row][column] == 'C';
    }

    /**
     * @param row row where the obstacle is being checked for
     * @param column column where the obstacle is being checked for
     * @return Indicator of if there is obstacle at the specified location
     * @requires row and column to be in the bounds of the maze
     * @ensures [obstacle unchanged]
     * and [Returns true if and only if there is a 'X' at the specified location]
     */
    public boolean hasObstacle (int row, int column){
        return maze[row][column] == 'X';
    }

    /**
     * @requires [maze != null]
     * @ensures [This method prints a visual representation of the maze]
     */
    public void display ( ){
        int i, n;
        for(n = 0; n < mazeRow + 2; n++){
            for(i = 0; i < mazeColumn + 2; i++){
                if (n == 0 || n == mazeRow + 1 || i == 0 || i == mazeColumn + 1)
                    System.out.print("X ");
                else
                    System.out.print(maze[n-1][i-1] + " ");
            }
            System.out.println();
        }
    }
}