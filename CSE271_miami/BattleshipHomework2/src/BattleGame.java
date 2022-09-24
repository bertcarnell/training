import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Class for the Game Battleship
 * @author bertc
 *
 */
public class BattleGame {

    /**
     * A simple message that is displayed at the beginning of the game to let
     * the player know the dimensions of the battle grid. This string can
     * be used as: System.out.printf(GRID_SIZE_MSG, gridSize);
     */
    private static final String GRID_SIZE_MSG =
            "Welcome to exceptional Battleship game.%n"
            + "The battle grid size is %d rows and columns.%n"
            + "Guess the row and column of the ship.%n";

    /**
     * Error message to be displayed if the user enters an illegal row or
     * column value. This string can be used as:
     * System.out.printf(GRID_SIZE_MSG, gridSize);
     */
    private static final String ILLEGAL_LOCATION =
            "The row and column must be in the range 0 to %d%n";

    /**
     * A message to be displayed to the player when the correct row
     * and column were correctly guessed.
     */
    private static final String SUCCESS_MSG =
            "You guessed the correct location of the battleship!";

    /**
     * A simple prompt that is displayed to the user to enable them to play
     * the game.
     */
    private static final String INPUT_PROMPT =
            "Enter input [r<num>, c<num>, grid, quit]: ";

    /**
     * A simple message that is displayed to the user to show that the column
     * value guessed is valid. This string is used with System.out.printf.
     */
    private static final String COL_CORRECT_MSG =
            "The column value of %d is correct%n";

    /**
     * A simple message that is displayed to the user to show that the row
     * value guessed is valid. This string is used with System.out.printf.
     */
    private static final String ROW_CORRECT_MSG =
            "The row value of %d is correct%n";

    /**
     * The size of the grid size that is being used by the battle grid. This
     * value is update in the printGridSize method.
     */
    private static int gridSize = 1;
    
    /**
     * The row value previously guessed by the user.
     */
    private static int guessRow = 0;
    
    /**
     * The column value previous guessed by the user.
     */
    private static int guessCol = 0;

    // You may add additional class variables as you see fit.
    
    /**
     * The minimum grid size of the game
     */
    private static int gridSizeMin = 8;
    /**
     * The maximum grid size for the game
     */
    private static int gridSizeMax = 20;
    
    // You may add additional helper methods as needed.
    
    /**
     * This is the top-level method that is called by the BattleGrid to enable
     * the user to play the game of guessing the correct row & column where
     * a battleship has been randomly hidden.
     * 
     * @param bg The battle grid (typically randomly generated) being used
     *     to play the game.
     */
    public static void play(BattleGrid bg) {
    	// variables to hold the guesses
    	ArrayList<Integer> row_guesses = new ArrayList<Integer>();
    	ArrayList<Integer> col_guesses = new ArrayList<Integer>();
    	
    	// find how many columns are available on the square grid
    	find_columns(bg);
    	
    	// Take input from the user
		try (Scanner in = new Scanner(System.in)) {
			// loop for each guess
			while (true) {
				// get input from the user
				promptForInput();
    	    	String line = in.nextLine();
    	    	
    	    	// if quit, exit
    	    	if (line.equals("quit")) {
    	    		break;
    	    	} else if (line.equals("grid")) {
    	    		// if grid, print the grid
    	    		printGrid(row_guesses, col_guesses);
    	    	} else {
    	    		// process a line, add to the guess lists, and process the guess 
    	    		processLine(line);
    	    		row_guesses.add(guessRow);
    	    		col_guesses.add(guessCol);
    	    		if (processGuess(bg)) {
    	    			break;
    	    		};
    	    	}
    		}
    	} catch (Exception e) {
    		// Exception could be thrown by processLine on an unexpected input
    		System.out.println(e.getMessage());
		}
    }
    
    /**
     * Find the number of columns in the grid
     * @param bg A BattleGrid object with the game parameters
     */
    protected static void find_columns(final BattleGrid bg)
    {
    	// guess from the minimum to the maximum
    	for (int i = gridSizeMin; i <= gridSizeMax; i++)
    	{
    		try {
    			// guess and catch an IllegalLocationException which would indicate we are out of bounds
    			bg.guess(0, i);
    		} catch (BattleGrid.IllegalLocationException e) {
    			// when we are out of bounds, we have found the grid size
    			printGridSize(i);
    			break;
    		} catch (Exception e) {
    			// Other exceptions are expected since we are missing the ships
    			// But do nothing with them
    		}
    	}
    }
    
    /**
     * Print the grid size and store it
     * @param newGridSize the found grid size
     */
    protected static void printGridSize(int newGridSize) {
    	gridSize = newGridSize;
    	System.out.printf(GRID_SIZE_MSG, gridSize);
    }
    
    /**
     * Prompt the user for input
     */
    protected static void promptForInput() {
    	System.out.printf(INPUT_PROMPT);
    }
    
    /**
     * Print the grid upon request
     * @param row_guesses A list of row guesses
     * @param col_guesses A list of column guesses
     */
    protected static void printGrid(final ArrayList<Integer> row_guesses, final ArrayList<Integer> col_guesses) {
    	// loop over the locations in the grid
    	System.out.println("");
    	for (int i = 0; i < gridSize; i++) {
    		for (int j = 0; j < gridSize; j++) {
    			// for each location in the grid, look for a match in the guesses
    			boolean foundmatch = false;
    			for (int k = 0; k < row_guesses.size(); k++) {
    				if (row_guesses.get(k).equals(i) & col_guesses.get(k).equals(j)){
    					foundmatch = true;
    				}
    			}
    			// if the match was found print X, otherwise print .
    			if (foundmatch) {
    				System.out.print("X");
    			} else {
    				System.out.print(".");
    			}
    		}
    		System.out.println("");
    	}
    }
    
    /**
     * Process a line of input from the user
     * @param line the line in format r<int> or c<int> or r<int> c<int> or c<int> r<int>
     * @throws Exception if the input is not an expected input
     */
    protected static void processLine(String line) throws Exception
    {
    	// trim spaces from the front and back
    	line.trim();
    	// find land marks in the expected input
    	int rloc = line.indexOf("r");
    	int cloc = line.indexOf("c");
    	int sloc = line.indexOf(" ");
    	int end = line.length();
    	// if there is a row and no space
    	if (rloc > -1 & sloc == -1)
    	{
    		guessRow = Integer.parseInt(line.substring(rloc + 1, end));
    	} else if (cloc > -1 & sloc == -1) {
    		// if there is a column and no space
    		guessCol = Integer.parseInt(line.substring(cloc + 1, end));
    	} else if (rloc > -1 & sloc > -1 & cloc > rloc){
    		// row and space and column after row
    		guessRow = Integer.parseInt(line.substring(rloc + 1, sloc));
    		guessCol = Integer.parseInt(line.substring(cloc + 1, end));
    	} else if (rloc > -1 & sloc > -1 & cloc > -1) {
    		// row and space and column before row
    		guessCol = Integer.parseInt(line.substring(cloc + 1, sloc));
    		guessRow = Integer.parseInt(line.substring(rloc + 1, end));
    	} else {
    		// Anthing else
    		throw new Exception("Unrecognized input"); ////////
    	}
    }
    
    /**
     * Return the last row guessed for testing
     * @return the guessed row
     */
    protected static int getGuessRow() {
    	return guessRow;
    }
    
    /**
     * Return the last column guessed for testing
     * @return the last row guessed
     */
    protected static int getGuessCol() {
    	return guessCol;
    }
    
    protected static int getGridSize() {
    	return gridSize;
    }
    
    /**
     * setter
     * @param row
     */
    protected static void setGuessRow(int row) {
    	guessRow = row;
    }
    
    /**
     * Setter
     * @param col
     */
    protected static void setGuessCol(int col) {
    	guessCol = col;
    }
    
    /**
     * Process a guess line
     * @param bg A BattleGrid guess
     * @return true if a successful guess, false otherwise
     */
    protected static boolean processGuess(BattleGrid bg) {
    	try {
    		// guess
    		bg.guess(guessRow, guessCol);
    		// if there was no exception, the it was successful
    		System.out.println(SUCCESS_MSG);
    		return(true);
    	} catch (BattleGrid.InvalidLocationException e) {
    		// a missed row and column
    		System.out.println(e.getMessage());
    	} catch (BattleGrid.InvalidRowException e) {
    		// a missed row with a good column
    		System.out.format(COL_CORRECT_MSG, guessCol);
    		System.out.println(e.getMessage());
    	} catch (BattleGrid.InvalidColException e) {
    		// a missed column with a good row
    		System.out.format(ROW_CORRECT_MSG, guessRow);
    		System.out.println(e.getMessage());
    	} catch (BattleGrid.IllegalLocationException e) {
    		// a guess outside the grid
    		System.out.format(ILLEGAL_LOCATION, gridSize); 
    		System.out.println(e.getMessage());
    	}
    	return(false);
    }
    
    /**
     * Reset guess row and col
     */
    protected static void reset() {
    	guessRow = 0;
    	guessCol = 0;
    }
}
