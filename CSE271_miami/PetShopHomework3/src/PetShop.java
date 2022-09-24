import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A simple pet store that has pets and food things.
 *
 */
public class PetShop {
    /**
     * The items available for sale in this pet shop. The items are 
     * added to this list via the addItemsFromFile method.
     */
    private ArrayList<Thing> things;

    /**
     * This is an intermediate summary string that has been used to 
     * generate the full summary format below. Don't use this one.
     * Instead, use the SUMMARY_FORMAT string below.
     */
    private static final String SUMMARY_SUB_FORMAT = 
            "    Number of pets      : %d%n"
            + "    Total price pets    : $%.2f%n"
            + "    Number of food items: %d%n"
            + "    Total price of food : $%.2f%n";

    /**
     * Format string to print summary of pets and food items in 
     * the pet store.
     */
    private static final String SUMMARY_FORMAT = 
            "Summary of items in Pet Shop%n"
            + "Aquatic pets & food summary%n"
            + SUMMARY_SUB_FORMAT
            + "Non-aquatic pets & food summary%n"
            + SUMMARY_SUB_FORMAT;

    /**
     * Format string to print food status for the pet store.
     */
    private static final String FOOD_STATUS = "Pet Shop food status:%n"
            + "    Daily aquatic food needed      : %.2f lb%n"
            + "    Daily non-aquatic food needed  : %.2f lb%n"
            + "    Aquatic food stock in store    : %.2f lb%n"
            + "    Non-aquatic food stock in store: %.2f lb%n";

    /**
     * Creates an empty shop without any items.
     */
    public PetShop() {
        things = new ArrayList<>();
    }
 
    /**
     * Returns the number of food objects in the list of things in 
     * this pet store.
     * 
     * @return The number of food things currently in the list of things in 
     *     this pet store. 
     */
    public int getFoodCount() {
    	int count = 0;
        for (int i = 0; i < things.size(); i++) {
        	if (things.get(i) instanceof Food) {
        		count += 1;
        	}
        }
        return count;        
    }
 
    /**
     * Returns the number of pet objects in the list of things in 
     * this pet store.
     * 
     * @return The number of pets currently in the list of things in 
     *     this pet store.
     */
    public int getPetCount() {
    	int count = 0;
        for (int i = 0; i < things.size(); i++) {
        	if (things.get(i) instanceof Pet) {
        		count += 1;
        	}
        }
        return count;        
    }

    
    /**
     * Adds items loaded from a given text file to the list of items in the
     * store. The items are stored line-by-line in the text file. Each line
     * contains values separated by a tab character. The data in the lines
     * are with:
     *    3-columns for Food:  FoodName Price Weight
     *    4-columns for Pet :  PetNamme PetKind  Price FoodPerDay 
     * 
     * @param fileName The text file from where Things are to be added to
     *     the list of items for sale in the pet store.
     */
    public void addItemsFromFile(String fileName) throws FileNotFoundException {
    	try (Scanner sc = new Scanner(new File(fileName))) {
    		// while the file has another line, add it to the output list
    		while (sc.hasNextLine()) {
    			processLine(sc.nextLine());
    		}
    	}
    }

    /**
     * Process a line from the input file
     * @param line a line for either a pet or a food
     */
	protected void processLine(String line) {
		ArrayList<String> linepieces = new ArrayList<String>();
		int begin = 0;
		while (line.indexOf("\t", begin) > -1) {
			linepieces.add(line.substring(begin, line.indexOf("\t", begin)));
			begin = line.indexOf("\t", begin) + 1;
		}
		if (begin < line.length()) {
			linepieces.add(line.substring(begin));
		}
		if (linepieces.size() == 3) {
			if (linepieces.get(0).equals("ChowBag")) {
				things.add(new ChowBag(Float.parseFloat(linepieces.get(1)), Float.parseFloat(linepieces.get(2))));
			} else if (linepieces.get(0).equals("WormCan")) {
				things.add(new WormCan(Float.parseFloat(linepieces.get(1)), Float.parseFloat(linepieces.get(2))));
			} else {
				System.out.println("Unexpected Food Type");
			}
		} else if (linepieces.size() == 4) {
			if (linepieces.get(0).equals("Dog")) {
				things.add(new Dog(linepieces.get(1), Float.parseFloat(linepieces.get(2)), Float.parseFloat(linepieces.get(3))));
			} else if (linepieces.get(0).equals("Cat")) {
				things.add(new Cat(linepieces.get(1), Float.parseFloat(linepieces.get(2)), Float.parseFloat(linepieces.get(3))));
			} else if (linepieces.get(0).equals("Fish")) {
				things.add(new Fish(linepieces.get(1), Float.parseFloat(linepieces.get(2)), Float.parseFloat(linepieces.get(3))));
			} else if (linepieces.get(0).equals("Octopus")) {
				things.add(new Octopus(linepieces.get(1), Float.parseFloat(linepieces.get(2)), Float.parseFloat(linepieces.get(3))));
			} else {
				System.out.println("Unexpected Pet Type");
			}
		} else {
			System.out.println("Unexpected Input File Condition");
		}
	}
    
    /**
     * Interface method to print a summary of the items in the pet store.
     * The summary is computed and printed using the supplied 
     * SUMMARY_FORMAT string.
     * 
     * @see SUMMARY_FORMAT
     */
    public void printSummary() {
    	ArrayList<Number> subAquatic = buildSubFormat(true);
    	ArrayList<Number> subNonAquatic = buildSubFormat(false);
    	
    	System.out.format(SUMMARY_FORMAT, subAquatic.get(0).intValue(), 
    			subAquatic.get(1).floatValue(),
    			subAquatic.get(2).intValue(),
    			subAquatic.get(3).floatValue(),
    			subNonAquatic.get(0).intValue(),
    			subNonAquatic.get(1).floatValue(),
    			subNonAquatic.get(2).intValue(),
    			subNonAquatic.get(3).floatValue());
    }
    
    /**
     * Build the data for the Output format
     * @param isAquatic is the pet or food aquatic
     * @return The list of numbers for the output format
     */
    protected ArrayList<Number> buildSubFormat(boolean isAquatic) {
    	int numPets = 0;
    	float pricePets = 0.0f;
    	int numFood = 0;
        float priceFood = 0.0f;
        
        for (int i = 0; i < things.size(); i++) {
        	if (things.get(i) instanceof Pet &
        			things.get(i).isAquatic() == isAquatic) {
        		numPets += 1;
        		pricePets += things.get(i).getPrice();
        	} else if (things.get(i) instanceof Food &
        			things.get(i).isAquatic() == isAquatic) {
        		numFood += 1;
        		priceFood += things.get(i).getPrice();
        	} else if (!(things.get(i) instanceof Thing)) {
        		System.out.println("Unexpected string sub format");
        	} // else, it might not have been the right aquatic level
        }
        
        ArrayList<Number> ret = new ArrayList<Number>();
        ret.add(Integer.valueOf(numPets));
        ret.add(Float.valueOf(pricePets));
        ret.add(Integer.valueOf(numFood));
        ret.add(Float.valueOf(priceFood));
        
        return ret;
    }
    
    /**
     * A simple method that prints all of the things in the store.
     */
    public void printAllThings() {
    	System.out.println("List of all items:");
    	for (int i = 0; i < things.size(); i++) {
    		System.out.println(things.get(i).toString());
    	}
    }
    
    /**
     * Computes and prints the amount of aquatic and non-aquatic food 
     * needed to feed all of the pets in the store along with the amount
     * of food currently available. The food needed by pets is computed
     * by adding the daily food needs of all the pets. The food available
     * is computed by adding the weight of all the food things.
     * 
     * @see FOOD_STATUS
     */
    public void reportFoodStatus() {
    	ArrayList<Float> aquatic = buildFoodStatus(true);
    	ArrayList<Float> nonaquatic = buildFoodStatus(false);
    	
        System.out.format(FOOD_STATUS, aquatic.get(0).floatValue(),
        		nonaquatic.get(0).floatValue(),
        		aquatic.get(1).floatValue(),
        		nonaquatic.get(1).floatValue());
        
    }
    
    /**
     * Build the output data for the food status
     * @param isAquatic is the food or pet aquatic
     * @return the list of data for the food status
     */
    protected ArrayList<Float> buildFoodStatus(boolean isAquatic) {
    	float dailyFoodNeeded = 0.0f;
    	float foodStock = 0.0f;
        
        for (int i = 0; i < things.size(); i++) {
        	if (things.get(i) instanceof Pet &
        			things.get(i).isAquatic() == isAquatic) {
        		dailyFoodNeeded += ((Pet) things.get(i)).getFoodPerDay();
        	} else if (things.get(i) instanceof Food &
        			things.get(i).isAquatic() == isAquatic) {
        		foodStock += ((Food) things.get(i)).getWeight();
        	} else if (!(things.get(i) instanceof Thing)) {
        		System.out.println("Unexpected string sub format");
        	} // else, it might not have been the right aquatic level
        }
        
        ArrayList<Float> ret = new ArrayList<Float>();
        ret.add(Float.valueOf(dailyFoodNeeded));
        ret.add(Float.valueOf(foodStock));
        
        return ret;
    }

    /**
     * Get all things
     * @return the list of things
     */
    protected ArrayList<Thing> getAllThings() {
    	return things;
    }
}
