import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A top-level textual User Interface (UI) class to perform various operations
 * on the MediaWarehouse class.
 * 
 * @note Students should not modify this class.
 * 
 * @author raodm
 *
 */
public class MediaMart {
    /**
     * The warehouse that manages all the media and bundles.
     */
    MediaWarehouse mw = new MediaWarehouse();

    /**
     * A common scanner object that is used to get inputs from System.in
     */
    private Scanner kbd = new Scanner(System.in);

    /**
     * A simple menu that let's the user know about different operations they
     * can perform on the media warehouse.
     */
    public static final String FULL_MENU = "Welcome to Media Mart. Main menu:\n"
            + "    1. Add media information from a file.\n"
            + "    2. Print currently loaded media.\n"
            + "    3. Search media for a given phrase.\n"
            + "    4. Write loaded media to a file.\n"
            + "    5. Calculate price for a media bundle.\n"
            + "    9. Print this detailed menu.\n" 
            + "    0. Quit.";

    /**
     * A simple input prompt that let's the user choose different operations
     * they would like to perform on a warehouse.
     */
    public static final String INPUT_PROMPT = 
            "\nWhat would you like to do (9: Show menu): ";

    /**
     * A simple welcome note that is printed once at the beginning.
     */
    public static final String WELCOME_NOTE = 
              "+-----------------------------+\n"
            + "|    Welcome to Media Mart    |\n"
            + "+-----------------------------+";

    /**
     * Helper method to load media from a given text or binary file. This method
     * prompts the user to enter the path and uses the file extension ".txt" to
     * determine if it is a text or binary file.
     * 
     * @see MediaWarehouse.loadMedia
     */
    private void addMedia() {
        System.out.print("Enter path to media text or binary file: ");
        final String filePath = kbd.next();
        boolean isText = filePath.endsWith(".txt");
        try {
            int added = mw.addMediaFrom(filePath, isText);
            System.out.println(added + " new media items added.");
        } catch (Exception exp) {
            System.out.println(
                    "Error loading media from " + filePath + ": " + exp);
        }
    }

    /**
     * A simple helper method to perform the search operation given a phrase.
     */
    private void search() {
        System.out.print("Enter search phrase: ");
        final String phrase = kbd.next();
        mw.search(phrase);
    }

    /**
     * This is an internal helper method that is used to write the the currently
     * loaded media to a file. The file can be a text or a binary file.
     */
    private void writeMedia() {
        System.out.print("Enter path to file to write media: ");
        final String filePath = kbd.next();
        boolean isText = filePath.endsWith(".txt");
        try {
            mw.writeMedia(filePath, isText);
        } catch (IOException exp) {
            System.out
                    .println("Error writing media to " + filePath + ": " + exp);
        }
    }

    /**
     * Internal helper method that obtains a list of UPCs from the user and
     * computes the bundle price for the given list of UPCs.
     */
    private void priceBundle() {
        System.out.print("Enter UPCs of media to price as a bundle: ");
        kbd.nextLine();
        final String[] upcs = kbd.nextLine().split("\\s");
        final ArrayList<String> upcList = new ArrayList<>(Arrays.asList(upcs));
        final float totPrice = mw.computeTotalPrice(upcList);
        System.out.printf("Price of bundle: %.2f%n", totPrice);
    }
    
    /**
     * This is the primary method that displays the menu and based on the user's
     * option this method performs different operations. Some of the operations
     * are performed by calling helper methods in this class.
     */
    public void menu() {
        do {
            System.out.print(MediaMart.INPUT_PROMPT);
            switch (kbd.nextInt()) {
            case 0: return; // quit
            case 1: addMedia();
                break;
            case 2: mw.printMedia();
                break;
            case 3: search();
                break;
            case 4: writeMedia();
                break;
            case 5: priceBundle();
                break;
            case 9: // Just print the full menu by default.
            default:
                System.out.println(MediaMart.FULL_MENU);
            }
        } while (true);
    }

    /**
     * The main method that starts up the media mart and allows the user
     * to perform various operations.
     * 
     * @param args The command-line arguments. These are currently unused.
     */
    public static void main(String[] args) {
        System.out.println(MediaMart.WELCOME_NOTE);
        MediaMart mm = new MediaMart();
        mm.menu();
    }
}
