
/**
 * A simple word search program that prints lines with matching:
 *    1. Phrases (or substrings) in a given line
 *    2. Search phrases that have wild-card characters (?)
 * 
 * @author DJ Rao
 * @version 0.1
 */
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a simple class that presents a menu and let's user perform
 * simple searches on lines in a given file.
 * 
 * @author raodm
 *
 */
public class SimpleSearch {
    /**
     * A simple string that contains the prompt for the menu used in this
     * program.
     */
    private static final String Menu = "\nWhat to do next:\n"
            + "1. Show all lines\n"
            + "2. Find lines with given phrase\n" 
            + "3. Quit\n"
            + "Enter your choice: ";

    /**
     * A simple prompt to obtain distance input from the user.
     */
    private static final String SearchPrompt = "Enter search phrase "
            + "(can contain wild-card characters): ";

    /**
     * A helper method to display a menu, obtain input from the user, and
     * perform the appropriate operation by calling helper methods in the
     * PointProcessor.java class.
     * 
     * @param ptList The list of points to be used by this method.
     */
    private static void menu(final ArrayList<String> srchList, Scanner in) {
        for (int menu = 0; menu != 3;) {
            System.out.print(Menu);
            menu = in.nextInt();
            switch (menu) {
            case 1:
                SearchHelper.print(srchList);
                break;

            case 2:
                System.out.print(SearchPrompt);
                final String phrase = in.next();
                ArrayList<String> matchList = 
                        SearchHelper.search(srchList,  phrase);
                SearchHelper.print(matchList);
                break;
            default:
                // Do nothing
            }
        }

    }

    /**
     * The main method that runs the point plotter program to permit the user to
     * process points from a text file.
     * 
     * @param args The command-line arguments are not really used.
     * 
     * @throws Exception This method just exposes exceptions that could be
     *                   thrown by helper methods.
     */
    public static void main(String[] args) throws Exception {
        // First load the list of points from a given data file.
        System.out.print("Enter text file name to load lines: ");
        String fileName; // file name read from user
        try (Scanner in = new Scanner(System.in)) {
            fileName = in.next();
            // Use helper method to load points.
            final ArrayList<String> srchList = SearchHelper.loadLines(fileName);
            // Print the information loaded for now.
            System.out.printf("Loaded %d lines from %s\n", srchList.size(),
                    fileName);
            // Next run the menu loop to let the user perform different
            // operations
            menu(srchList, in);
        }
    }
}
