import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		MediaLibrary ml = new MediaLibrary();
		try {
			ml.addMediaFrom("mediaHw2.txt");
			
			ArrayList<Media> mediaList = ml.getMediaList();
			LinkedList<Media> mediaLinkedList = new LinkedList<Media>();
			for (Media m : mediaList) {
				mediaLinkedList.add(m);
			}
			
			System.out.println("UNSORTED");
			mediaLinkedList.print();
			
			mediaLinkedList.sort();
			
			System.out.println("\nSORTED");
			mediaLinkedList.print();
			
			System.out.println("\n\nDOUBLY LINKED");

			DoublyLinkedList<Media> mediaDoublyLinkedList = new DoublyLinkedList<Media>();
			for (Media m : mediaList) {
				mediaDoublyLinkedList.add(m);
			}
			
			System.out.println("\nUNSORTED");
			mediaDoublyLinkedList.print();
			
			System.out.println("\nUNSORTED BACKWARDS");
			mediaDoublyLinkedList.printBackwards();

			mediaDoublyLinkedList.sort();

			System.out.println("\nSORTED");
			mediaDoublyLinkedList.print();

			System.out.println("\nSORTED BACKWARDS");
			mediaDoublyLinkedList.printBackwards();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
