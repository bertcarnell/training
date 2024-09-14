import java.util.Scanner;

public class MediaUI {
    private MediaLibrary ml;
    private Scanner kbd;

    public static final String MENU = "Main menu:\n"
    		+ "    1. Load media\n"
            + "    2. View the list of books and videos\n"
            + "    3. Delete a book or video\n"
            + "    4. Add a book\n"
            + "    5. Add a video\n"
            + "    6. Save changes to the list to a file\n"
            + "    7. Exit\n" 
            + "    8. Show medu\n";

    public static final String INPUT_PROMPT = 
            "\nSelection (8: Show menu): ";
    
    public MediaUI() {
    	ml = new MediaLibrary();
    	kbd = new Scanner(System.in);
    }

    private void loadMedia() {
        System.out.print("Enter file name:");
        final String filePath = kbd.next();
        try {
            ml.addMediaFrom(filePath);
        } catch (Exception e) {
            System.out.println(
                    "Error loading media from " + filePath + ": " + e);
        }
    }
    
    private void printMedia() {
    	ml.printMedia();
    }
    
    private void deleteMedia() {
    	System.out.println("Enter the unique ID of the media to delete: ");
    	final String id  = kbd.next();
    	try {
    		ml.deleteMedia(Integer.valueOf(id));
    	}
    	catch (Exception e) {
    		System.out.println("Deletion Failed.  ID " + id + " Not found.");
    	}
    }
    
    private void addBook() throws Exception {
    	int id = ml.getMaxID() + 1;
    	System.out.println("Enter the title of the book: ");
    	String title = kbd.next() + kbd.nextLine();
    	System.out.println("Enter the author: ");
    	final String author = kbd.next() + kbd.nextLine();
    	System.out.println("Enter the publisher: ");
    	final String publisher = kbd.next() + kbd.nextLine();
    	ml.addMedia(new Book(title, id, author, publisher));
    }
    
    private void addVideo() throws Exception {
    	int id = ml.getMaxID() + 1;
    	System.out.println("Enter the title of the video: ");
    	final String title = kbd.next() + kbd.nextLine();
    	System.out.println("Enter the director: ");
    	final String author = kbd.next() + kbd.nextLine();
    	System.out.println("Enter the format (Digital, DVD, Blu-Ray): ");
    	final String publisher = kbd.next() + kbd.nextLine();
    	ml.addMedia(new Book(title, id, author, publisher));
    }

    private void writeMedia() {
        System.out.print("Enter file name to write media inventory: ");
        final String filePath = kbd.next();
        try {
            ml.writeMedia(filePath);
        } catch (Exception e) {
            System.out.println("Error writing media to " + filePath + ": " + e);
        }
    }

    public void menu() throws NumberFormatException, Exception {
        do {
            System.out.print(MediaUI.INPUT_PROMPT);
            switch (kbd.nextInt()) {
	            case 1: loadMedia();
	                break;
	            case 2: printMedia();
	                break;
	            case 3: deleteMedia();
	                break;
	            case 4: addBook();
	                break;
	            case 5: addVideo();
	            	break;
	            case 6: writeMedia();
	                break;
	            case 7: return;
	            case 8: System.out.println(MediaUI.MENU);
	            default: System.out.println(MediaUI.MENU);
            }
        } while (true);
    }

    public static void main(String[] args) {
        MediaUI mu = new MediaUI();
        System.out.print(MediaUI.MENU);
        try {
        	mu.menu();
        }
        catch (Exception e) {
        	System.out.println("Error: " + e.getMessage());
        }
    }
}
