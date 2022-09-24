import java.util.Random;

/**
 * This class represents a grid for playing a simplified form of the battleship
 * game. This class generates a random battle grid in which an battleship is
 * hidden at a random location. The task of the player is to guess the location
 * of the battleship.
 * 
 * @note An interesting aspect of this class is that its Application Program
 *       Interface (API) uses exceptions to convey different scenarios rather
 *       than just using return values.
 * 
 * @note The grid is always square. However, its dimensions are randomly
 *       determined to be between 8 and 20 positions.
 * 
 * @author raodm
 */
public class BattleGrid {
    /**
     * The grid size to be used for game play. This value is either randomly
     * computed or determined from command-line arguments.
     */
    private int gridSize;

    /**
     * The hidden location of the ship row. This value is in the range 0 to
     * gridSize - 1. The ship's hidden row is either randomly computed or
     * determined for command-line arguments.
     */
    private int shipRow;

    /**
     * The hidden location of the ship column. This value is in the range 0 to
     * gridSize - 1. The ship's hidden column is either randomly computed or
     * determined for command-line arguments.
     */
    private int shipCol;

    /**
     * The constructor for BattleGrid can only be called from the main method in
     * this class. The constructor sets up the dimension of the battle grid and
     * the hidden position of the battleship within the grid.
     * 
     * @param args The command-line arguments for fixing dimensions and location
     *             of the ship. The fixed settings are used to ease repeatable
     *             testing. If the args are not specified, then this method
     *             generates locations using random values.
     */
    private BattleGrid(final String[] args) {
        if ((args != null) && (args.length > 2)) {
            // Use values specified as command-line arguments to fixed
            // settings so as to ease repeatable testing.
            gridSize = Integer.parseInt(args[0]);
            shipRow = Integer.parseInt(args[1]);
            shipCol = Integer.parseInt(args[2]);
        } else {
            // Generate an random grid size between 8 and 20.
            final Random rnd = new Random();
            gridSize = rnd.nextInt(12) + 8;
            // Now that we know the grid size, let's generate the row
            // and column for the battleship
            shipRow = 1 + rnd.nextInt(gridSize);
            shipCol = 1 + rnd.nextInt(gridSize);
        }
    }

    /**
     * The only interface method in this class that enables a player to guess
     * the row and column of the battleship.
     * 
     * @note This method does not throw any exceptions if the specified
     *     row and column are correct.
     * 
     * @param row The row value guessed by the user.
     * @param col The column value guessed by the user.
     * 
     * @throws BattleGrid.InvalidLocationException This exception is thrown if
     *                                             both the row and column
     *                                             guessed are incorrect.
     * 
     * @throws BattleGrid.InvalidRowException      This exception is thrown if
     *                                             the row guessed is incorrect.
     * 
     * @throws BattleGrid.InvalidColException      This exception is thrown if
     *                                             the column guessed is
     *                                             incorrect.
     * 
     * @throws BattleGrid.IllegalLocationException This exception is thrown if
     *                                             the row or column value is
     *                                             not in the range 0 to
     *                                             gridSize-1 (inclusive)
     * 
     */
    public void guess(final int row, final int col)
            throws BattleGrid.InvalidLocationException,
            BattleGrid.InvalidRowException, BattleGrid.InvalidColException,
            BattleGrid.IllegalLocationException {
        if ((row < 0) || (col < 0) || (row >= gridSize) || (col >= gridSize)) {
            // The position is not valid.
            throw new IllegalLocationException();
        }
        // If both row and column is different throw an invalid location
        // exception.
        if ((row != shipRow) && (col != shipCol)) {
            throw new InvalidLocationException(shipRow - row, shipCol - col);
        }

        // Check if the row is at the right location. If not throw an exception
        // with the appropriate message
        if (row != shipRow) {
            throw new InvalidRowException(shipRow - row);
        }
        // Check if the row is at the right location. If not throw an exception
        // with the appropriate message
        if (col != shipCol) {
            throw new InvalidColException(shipCol - col);
        }
    }

    public static void main(String[] args) {
        // Generate a battle grid for this game.
        BattleGrid grid = new BattleGrid(args);
        // Let the game's main method take over
        BattleGame.play(grid);
    }

    /**
     * Helper method to generate a message for an exception.
     * 
     * @param msg   The message to be formatted with "lower" or "higher" string.
     * 
     * @param delta The value to be used to generate "lower" or "higher" string.
     * 
     * @return The message to be included in an exception.
     */
    private static String getMsg(final String msg, final int delta) {
        return String.format(msg, (delta < 0 ? "lower" : "higher"));
    }

    /**
     * A simple message that is formatted to generate the message to be included
     * in exceptions.
     */
    private static final String INVALID_ROW_MSG = "Invalid row. It must be %s";

    /**
     * A simple message that is formatted to generate the message to be included
     * in exceptions.
     */
    private static final String INVALID_COL_MSG = "Invalid column. "
            + "It must be %s";

    /**
     * A simple exception that is thrown by the guess method if the guessed row
     * and column are incorrect..
     */
    public static class IllegalLocationException extends Exception {
        // A dummy serialization ID to keep Eclipse happy.
        private static final long serialVersionUID = 1L;

        /**
         * A simple constructor that sets up a fixed message.
         */
        public IllegalLocationException() {
            super("Invalid row or column value specified.");
        }
    }

    /**
     * A simple exception that is thrown by the guess method if the guessed row
     * and column are incorrect..
     */
    public static class InvalidLocationException extends Exception {
        // A dummy serialization ID to keep Eclipse happy.
        private static final long serialVersionUID = 1L;

        /**
         * Constructor used to generate an exception with a suitably formatted
         * exception message based on the difference in the guessed value of
         * row and column.
         * 
         * @param rowDelta The difference in row guess.
         * @param colDelta The difference in column guess.
         */
        public InvalidLocationException(int rowDelta, int colDelta) {
            super(getMsg(INVALID_ROW_MSG, rowDelta) + "\n"
                    + getMsg(INVALID_COL_MSG, colDelta));
        }
    }

    /**
     * A simple exception that is thrown by the guess method if the guessed row
     * is not the same as the ship's row.
     */
    public static class InvalidRowException extends Exception {
        /**
         * Constructor to initialize the next occurring index position.
         * 
         * @param c The character guessed by the user which is not present in
         *          the secret word.
         */
        public InvalidRowException(int delta) {
            super(getMsg(INVALID_ROW_MSG, delta));
        }

        // A dummy serialization ID to keep Eclipse happy.
        private static final long serialVersionUID = 1L;
    }

    /**
     * A simple exception that is thrown by the guess method if the guessed
     * column is not at the correct location.
     */
    public static class InvalidColException extends Exception {
        /**
         * Constructor to initialize the next occurring index position.
         * 
         * @param c The character guessed by the user which is not present in
         *          the secret word.
         */
        public InvalidColException(int delta) {
            super(getMsg(INVALID_COL_MSG, delta));
        }

        // A dummy serialization ID to keep Eclipse happy.
        private static final long serialVersionUID = 1L;
    }
}
