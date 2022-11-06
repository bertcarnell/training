import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class acts as a warehouse that maintains the list of media items
 * Media items can be added, searched, and priced.
 */
public class MediaWarehouse {
    /**
     * The list of media currently available in this warehouse. This list is
     * initialized to an empty list in the constructor. 
     */
    private ArrayList<Media> mediaList;
    
    private static final String DUP_STRING = "Duplicate media with upc %s ignored.";
    		
    /**
     * The default constructor merely initializes the instance variables.
     */
    public MediaWarehouse() {
        mediaList  = new ArrayList<>();
    }

    /**
     * Helper method to find the media object associated with a given upc
     *     in the mediaList.
     * 
     * @param upc The UPC to search for in the current mediaList.
     * @return This method returns the media item if it is found. Otherwise,
     *     this method returns null.
     */
    public Media findMedia(String upc) {
    	for (Media med: mediaList) {
    		if (med.getUpc().equals(upc)) {
    			return med;
    		}
    	}
        return null;  // when media with given upc not found.
    }

    /**
     * Print the list of media in the mediaList.
     */
    public void printMedia() {
    	for (Media med: mediaList) {
    		System.out.println(med.toString());
    	}
    }
    
    /**
     * Searches for the given phrase in each media's UPC and title. The method 
     * prints the media objects that contain the phrase. This method also
     * prints a summary message of the form -- 
     * "Found 5 matches, out of 17 media items." at the end. See sample outputs
     * in project document for more examples.
     * 
     * @note The search is case-insensitive. So all strings must be first
     *     converted to lower case prior to using them.
     * 
     * @param phrase The substring to search for.
     */
    public void search(String phrase) {
    	int count = 0;
    	
    	System.out.println("Media with the phrase are: ");
    	
        // Convert sub-name to lower case to streamline the search
    	for (Media med: mediaList) {
    		if (med.getUpc().toLowerCase().contains(phrase.toLowerCase()) |
    				med.getTitle().toLowerCase().contains(phrase.toLowerCase())) {
    			System.out.println(med.toString());
    			count += 1;
    		}
    	}
 
        // Print the number of bundles found.
        System.out.println("Found " + count + " matches, out of "
                + mediaList.size() + " media items.");
    }
        
    /**
     * This method is called to load data from either a text file or a binary
     * file. For text file formats see 90s_media.txt for example. The binary 
     * file is read as a single object (using an ObjectInputStream) of 
     * ArrayList type. The media loaded is added to the mediaList 
     * instance variable in this class. This method does not add duplicate 
     * media items -- i.e. media with the same UPC are duplicates. If a 
     * duplicate media item is found it prints an message stating with the 
     * duplicate upc in the form -- "Duplicate media with upc m_90_2 ignored".
     * 
     * @see Video.load
     * @see Audio.load
     * 
     * @note It may be best to create a helper method that checks and adds
     *     non-duplicate media.
     * 
     * @param filePath Path (relative or absolute) to the file from where the
     *                 media is to be added to the current
     * @param isText   If this flag is true, then the file is a text file.
     *                 Otherwise the file is a binary file (created via prior
     *                 call to the writeMedia method in this class.
     * 
     * @return This method returns the number of new media items added 
     *     from the file.
     *     
     * @throws IOException When I/O errors occur
     * @throws ClassNotFoundException When loading binary media object fails
     */
	public int addMediaFrom(String filePath, boolean isText) 
            throws IOException, ClassNotFoundException {
    	int added = 0;
    	File file = new File(filePath);
    	if (isText) {
    		try (Scanner sc = new Scanner(file)) {
    			while (sc.hasNextLine()) {
    				added += processLine(sc.nextLine());
    			}
    		}
    	}
    	else {
    		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
    			ArrayList<Media> new_media = (ArrayList<Media>) ois.readObject();
    			for (Media med: new_media) {
    				added += processLine(med.toString());
    			}
    		}
    	}
        return added;
    }
    
    private int processLine(String line) throws ClassNotFoundException {
    	try (Scanner sc = new Scanner(line)) {
    		sc.useDelimiter("\t");
    		// need to trim the token because the "Video " on Mother's day had a space in it
    		String mediaClass = sc.next().trim();
    		Media testMedia;
    		if (mediaClass.equals("Audio")) {
    			testMedia = Audio.load(line);
    		}
    		else if (mediaClass.equals("Video")) {
    			testMedia = Video.load(line);
    		}
    		else {
    			// silently don't load
    			return 0;
    		}
    		
    		// look for existing one
    		Media inList = findMedia(testMedia.getUpc());
    		// if not there, add it
    		if (inList == null) {
    			mediaList.add(testMedia);
    			return 1;
    		}
    		// else already found
    		System.out.println(String.format(DUP_STRING, testMedia.getUpc()));
    		return 0;
    	}
    }
   
    /**
     * This method is called to write each entry in the mediaList to a given
     * file in text or binary format. The text format is generated by simply
     * printing each media entry. The binary format is generated by writing
     * the whole mediaList ArrayList as a single object using an 
     * ObjectOutputStream.
     * 
     * @param filePath The path to the output file. If the file exists, then the
     *     contents is overwritten.
     * @param isText If this flag is true, then the output is written as text.
     *     Otherwise the output is written in binary format.
     *     
     * @throws IOException This exception is exposed if any I/O error occurs.
     */
    public void writeMedia(String filePath, boolean isText) throws IOException {
    	if (isText) {
    		try (PrintWriter pw = new PrintWriter(filePath)) {
    			for (Media med: mediaList) {
    				pw.println(med.toString());
    			}
    		}
    		
    	}
    	else {
    		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
   				oos.writeObject(mediaList);
    		}
    	}
    }
    
    /**
     * <p>Computes the total price of a set of media items to be purchased as a
     * bundle. Note that a totalPrice is computed by adding the price for each
     * media item. After that, the following formula is used to adjust the price
     * of the bundle:
     * </p>
     * <p>
     * finalPrice = totalPrice + (hdcpCount * 0.10) - (operaCount * 0.05)
     * </p>
     * where, hdcpCount is number of Video objects for which isHdcp returns true
     * and operaCount is number of Audio objects for which isOperatic is true
     * 
     * @param upcList The list of items whose total price is to be computed.
     * @return The total price of the list of items.
     */
    public float computeTotalPrice(ArrayList<String> upcList) {
    	float costPerHdcp = 0.10f;
    	float costPerOpera = 0.05f;
    	float totalPrice = 0.0f;
    	int hdcpCount = 0;
    	int operaCount = 0;
    	
    	for (String myupc: upcList) {
    		Media med = findMedia(myupc);
    		totalPrice += med.getPrice();
    		if (med instanceof Audio) {
    			if (((Audio) med).isOperatic()) {
    				operaCount += 1;
    			}
    		}
    		else if (med instanceof Video) {
    			if (((Video) med).isHdcp()) {
    				hdcpCount += 1;
    			}
    		}
    		// else silently skip
    	}
        return totalPrice + (((float) hdcpCount) * costPerHdcp) - (((float) operaCount) * costPerOpera);  // dummy return; remove it.
    }
}
