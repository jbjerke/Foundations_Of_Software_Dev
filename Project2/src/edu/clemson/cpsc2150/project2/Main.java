package edu.clemson.cpsc2150.project2;

/*
 * Jordan Bjerken
 * Section 01
 * CPSC 2150 Project 2
 * 3/5/17
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        // Create an instance of maze
        Maze ratMaze;

        // Variables
        int direction; //0 - down, 1 - left, 2 - up, 3 - right
        int row, column;
        String impl;

        // Determine which implementation to use
        System.out.print("Use which maze implementation (1 or 2)? ");
        Scanner implScan = new Scanner(System.in);
        impl = implScan.nextLine();

        // Create an instance of Maze
        if (Integer.parseInt(impl) == 1)
            ratMaze = new MazeImpl1( );
        else
            ratMaze = new MazeImpl2( );

        // Variables for reading in the command
        Scanner commScan = new Scanner(System.in);
        String comm;

        // Display the maze
        ratMaze.display();

        // Read in a command
        System.out.print(">> ");
        comm = commScan.nextLine();

        // Create an instance of the command class
        Command command = new Command(comm);

        // Loop for the maze process until the user enters the exit command
        while(command.getCommandType() != Command.CommandType.EXIT){

            // Ensure that the command is valid
            if(command.isValid()){

                // Get the row and column from the command line
                row = command.getRow( );
                column = command.getColumn( );

                // Ensure the entered row and column are in the bounds of the maze
                if(row >= 0 && row < 5 && column >= 0 && column < 5){
                    switch (command.getCommandType()){
                        case PLACE:
                            // Place a rat if there is not already a rat
                            if(command.getMazeItem() == Command.MazeItem.RAT)
                                if(!ratMaze.hasRat(row, column))
                                    ratMaze.placeRat(row, column);
                                else
                                    System.out.println("A rat is already at " + row + " " + column + ".");

                                // Place cheese
                            else if(command.getMazeItem() == Command.MazeItem.CHEESE)
                                if(!ratMaze.hasCheese(row, column))
                                    ratMaze.placeCheese(row, column);
                                else
                                    System.out.println("A piece of cheese is already at " + row + " " + column + ".");

                                // Place an obstacle
                            else if(command.getMazeItem() == Command.MazeItem.OBSTACLE)
                                if(!ratMaze.hasObstacle(row,column))
                                    ratMaze.placeObstacle(row, column);
                                else
                                    System.out.println("An obstacle is already at " + row + " " + column + ".");

                            break;

                        // Remove an item at the user specified location
                        case REMOVE:
                            ratMaze.remove(row, column);

                            break;

                        case TURN:
                            switch (command.getTurnDirection()) {
                                // Rotate the mouse clockwise
                                case CLOCKWISE:
                                    direction = ratMaze.getRatDirection();

                                    direction++;

                                    // Make sure the direction is between 0 and 3
                                    if (direction == 4)
                                        direction = 0;

                                    ratMaze.turnRat(direction);

                                    break;

                                // Rotate the mouse counterclockwise
                                case COUNTERCLOCKWISE:
                                    direction = ratMaze.getRatDirection();

                                    direction--;

                                    // Make sure the direction remains between 0 and 3
                                    if (direction == -1)
                                        direction = 3;

                                    ratMaze.turnRat(direction);

                                    break;

                                default:
                                    System.out.println("Invalid direction.");
                            }

                            break;

                        // Move the mouse if possible
                        case MOVE:
                            if(ratMaze.canRatMove()){
                                ratMaze.moveRat();
                            }
                            else
                                System.out.println("The rat can not move.");

                            break;

                        default:
                            System.out.println("Error: Please enter a valid command.");
                    }
                }
                else
                    System.out.println("Out of the maze bounds.");
            }
            else{
                System.out.println(command.getErrorMessage());
            }

            // Reprint modified maze
            ratMaze.display();

            // Get a new command
            System.out.print(">> ");
            comm = commScan.nextLine();

            // Create a new instance of the command class for the new command
            command = new Command(comm);
        }
    }
}
