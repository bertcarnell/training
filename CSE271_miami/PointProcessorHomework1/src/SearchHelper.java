import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a class that has helper methods to enable a simple search 
 * functionality. The methods in this class are called by the SimpleSearch
 * class based on user's input choices. The methods in this class are to be 
 * implemented and documented by students. 
 *  NOTE: Ensure you add Javadoc for each method in the starter code and for
 *  any methods you may add.
 */
public class SearchHelper {
    
	/**
	 * Load the lines from a file into an ArrayList
	 * @param fileName the String filename
	 * @return an ArrayList containing the lines of the file
	 * @throws FileNotFoundException when the the file is not found
	 */
    public static ArrayList<String> loadLines(final String fileName)
            throws FileNotFoundException {
    	
    	ArrayList<String> output = new ArrayList<String>();
    	// read the file
    	try (Scanner sc = new Scanner(new File(fileName)))
    	{
    		// while the file has another line, add it to the output list
    		while (sc.hasNextLine())
    		{
    			output.add(sc.nextLine());
    		}
    	}
        return output;
    }

    /**
     * Prints the ArrayList.
     * @param lines The ArrayList with the lines being searched.
     */
    public static void print(final ArrayList<String> lines) {
    	if (lines.size() == 0) {
    		System.out.println("Empty List");
    	}
    	// print each line of the ArrayList
        for (int i = 0; i < lines.size(); i++) {
            System.out.print("[" + i + "]: " + lines.get(i) + "\n");
        }
    }

    /**
     * Searches the ArrayList for the parameter phrase and returns the line
     * with the phrase in it.
     * @param srchList An ArrayList containing the strings being searched.
     * @param phrase The string being searched for.
     * @return The lines which contains the phrase being searched for.
     */
    public static ArrayList<String> search(final ArrayList<String> srchList, 
            String phrase) {
        // Creates a new ArrayList to hold lines containing the target phrase.
        ArrayList<String> line = new ArrayList<String>();
        if (phrase.isEmpty()) {
        	return line;
        }
        // For loop cycling through srchList.
        for (int i = 0; i < srchList.size(); i++) {
            // Checks each string for instances of phrase.
            if (containsPhrase(srchList.get(i), phrase)) {
                // Adds a string to line if it contains the target phrase.
                line.add(srchList.get(i));
            }
        }
        // Returns an ArrayList with all the lines containing phrase in it.
        return line;
    }

    /**
     * Searches a string for a phrase. Returns true if that string contains
     * the phrase, false if not.
     * @param line A String from the ArrayList being searched. 
     * @param phrase The phrase being searched for. Also a string.
     * @return A boolean stating true if the line parameter contains the
     *         phrase parameter, false if not.
     */
    public static boolean containsPhrase(String line, String phrase) {
    	// If the phrase has no wildcards, then simply check for the phrase in the line
    	if (!phrase.contains("?")) {
    		return line.contains(phrase);
    	}
    	// else if there are wildcards present
    	else {
    		char[] lineArray = line.toCharArray();
    		char[] phraseArray = phrase.toCharArray();
    		// loop over the line
    		for (int i = 0; i < lineArray.length - phraseArray.length; i++) {
    			// if the first character is found to match, then search the rest
    			if (compareWithWild(lineArray[i], phraseArray[0])) {
    	    		boolean foundfull = true;
    	    		// looping through the phrase, if any are found to not match, then we will not return true
    				for (int j = 0; j < phraseArray.length; j++) {
    					if (!compareWithWild(lineArray[i+j], phraseArray[j])) {
    						foundfull = false;
    					}
    				}
    				if (foundfull) {
    					return true;
    				}
    			}
    		}
    		return false;
    	}
    }
    
    /**
     * Allow for a character comparison with a ? wildcard 
     * @param a the target character
     * @param b the search character (can contain ?)
     * @return whether the characters match
     */
    public static boolean compareWithWild(char a, char b)
    {
    	if (a == b) {
    		return true;
    	} 
    	else if (b == '?') {
    		return true;
    	} 
    	else {
    		return false;
    	}
    }
}
