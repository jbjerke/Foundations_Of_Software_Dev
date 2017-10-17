package edu.clemson.cpsc2150.project1;

public class Command {
	// To parse a command, simply instantiate this class and pass the
	// raw string input as a parameter to the constructor
	// 
	// The resulting object will represent that single command and
	// cannot be reused. To process a new command, create a new object.
	//
	// WARNING: This class does NOT check to ensure that row
	// and column indices are within the bounds of the maze.
	// You must do this check within your own code.
	
	
    public enum CommandType { NONE, PLACE, REMOVE, TURN, MOVE, EXIT };
    public enum MazeItem { NONE, RAT, CHEESE, OBSTACLE };
    public enum TurnDirection { NONE, CLOCKWISE, COUNTERCLOCKWISE };

    private boolean myValidFlag = true;
    private String myErrorMessage = "No error";
    private CommandType myCommandType = CommandType.NONE;
    private MazeItem myMazeItem = MazeItem.NONE;
    private TurnDirection myTurnDirection = TurnDirection.NONE;
    private int myRow = 0, myColumn = 0;
	
    public Command(String cmdText) {
        String[] tokens = cmdText.trim().toLowerCase().split("\\W+");
        processCommand(tokens);
    }

	// Returns true if the input string was successfully parsed as a
	// recognized command; otherwise, false
    public boolean isValid() {
        return myValidFlag;
    }

	// Returns an error message that describes why the command
	// is invalid, if applicable
    public String getErrorMessage() {
        return myErrorMessage;
    }

	// Returns the type of the command, i.e., place, remove, turn, etc.
    public CommandType getCommandType() {
        return myCommandType;
    }

	// Returns the item for a "place" command
    public MazeItem getMazeItem() {
        return myMazeItem;
    }

	// Returns the direction for a "turn" command
    public TurnDirection getTurnDirection() {
        return myTurnDirection;
    }

	// Returns the row index for a "place" or "remove" command
    public int getRow() {
        return myRow;
    }

	// Returns the column index for a "place" or "remove" command
    public int getColumn() {
        return myColumn;
    }


    /* PRIVATE METHODS */

    private void processCommand(String[] cmdTokens) {
        switch (cmdTokens[0]) {
            case "place":
                myCommandType = CommandType.PLACE;
                if (cmdTokens.length >= 4) {
                    switch (cmdTokens[1]) {
                        case "rat":
                            myMazeItem = MazeItem.RAT;
                            break;
                        case "cheese":
                            myMazeItem = MazeItem.CHEESE;
                            break;
                        case "obstacle":
                            myMazeItem = MazeItem.OBSTACLE;
                            break;
                        default:
                            myValidFlag = false;
                            myErrorMessage = "Valid items to place: rat, cheese, obstacle";
                            break;
                    }
                    processCoordinates(cmdTokens[2], cmdTokens[3]);
                } else {
                    myValidFlag = false;
                    myErrorMessage = "Usage: place [item] [row] [column]";
                }
                break;
            case "remove":
                myCommandType = CommandType.REMOVE;
                if (cmdTokens.length >= 3) {
                    processCoordinates(cmdTokens[1], cmdTokens[2]);
                } else {
                    myValidFlag = false;
                    myErrorMessage = "Usage: remove [row] [column]";
                }
                break;
            case "turn":
                myCommandType = CommandType.TURN;
                if (cmdTokens.length >= 2) {
                    switch (cmdTokens[1]) {
                        case "cw":
                            myTurnDirection = TurnDirection.CLOCKWISE;
                            break;
                        case "ccw":
                            myTurnDirection = TurnDirection.COUNTERCLOCKWISE;
                            break;
                        default:
                            myValidFlag = false;
                            myErrorMessage = "Valid directions: cw, ccw";
                            break;
                    }
                } else {
                    myValidFlag = false;
                    myErrorMessage = "Usage: turn [direction]";
                }
                break;
            case "move":
                myCommandType = CommandType.MOVE;
                break;
            case "exit":
                myCommandType = CommandType.EXIT;
                break;
            default:
                myValidFlag = false;
                myErrorMessage = "Unknown command.";
                break;
        }
    }

    private void processCoordinates(String row, String column) {
        try {
            myRow = Integer.parseInt(row);
            myColumn = Integer.parseInt(column);
        } catch (NumberFormatException e) {
            myValidFlag = false;
            myErrorMessage = "Invalid coordinates.";
        }
    }
}
