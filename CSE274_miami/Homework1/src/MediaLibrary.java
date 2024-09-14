import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MediaLibrary {
	private ArrayList<Media> mediaList;
	private final int numberOfLinesInGroup = 5;
	private final int positID = 0;
	private final int positTitle = 1;
	private final int positType = 2;
	private final int positInfo4 = 3;
	private final int positInfo5 = 4;
	
	public MediaLibrary() {
		mediaList = new ArrayList<Media>();
	}
	
	public void addMediaFrom(String filePath) throws Exception {
		File file = new File(filePath);
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				// Read 5 lines at a time
				ArrayList<String> lineGroup = new ArrayList<String>(numberOfLinesInGroup);
				for (int i = 0; i < numberOfLinesInGroup; i++) {
					if (sc.hasNextLine()) {
						lineGroup.add(sc.nextLine());
					}
				}
				if (lineGroup.size() == numberOfLinesInGroup) {
					processLineGroup(lineGroup);
				}
				else {
					System.out.println("Invalid lines in " + filePath);
					return;
				}
			}
		}
	}
	
	private void processLineGroup(ArrayList<String> lineGroup) throws Exception {
		int id = Integer.valueOf(lineGroup.get(positID));
		String title = lineGroup.get(positTitle);
		String type = lineGroup.get(positType);
		String info4 = lineGroup.get(positInfo4);
		String info5 = lineGroup.get(positInfo5);
		Media med = new Media();
		if (type.equalsIgnoreCase("book")) {
			med = new Book(title, id, info4, info5);
		}
		else if (type.equalsIgnoreCase("video")) {
			med = new Video(title, id, info4, info5);
		}
		else {
			throw new Exception("Error in media type. " + type + " was given.");
		}
		mediaList.add(med);
	}
	
	public void printMedia() {
		for (Media med : mediaList) {
			med.printMedia();
		}
	}
	
	public void deleteMedia(int uniqueID) throws Exception {
		for (int i = 0; i < mediaList.size(); i++) {
			if (mediaList.get(i).getUniqueID() == uniqueID) {
				mediaList.remove(i);
				return;
			}
		}
		// If we made it here, it was not found
		throw new Exception("Unique ID not found: " + uniqueID);
	}
	
	public int getMaxID() {
		int maxid = 0;
		for (Media med : mediaList) {
			if (med.getUniqueID() > maxid) {
				maxid = med.getUniqueID();
			}
		}
		return maxid;
	}
	
	public void addMedia(Media med) throws Exception {
		// check that unique id isn't already present
		int uniqueid = med.getUniqueID();
		for (Media medi : mediaList) {
			if (medi.getUniqueID() == uniqueid) {
				throw new Exception("Unique id " + uniqueid + " Already found");
			}
		}
		mediaList.add(med);
	}
	
	public void writeMedia(String filepath) throws FileNotFoundException {
		try (PrintWriter pw = new PrintWriter(filepath)) {
			for (Media med : mediaList) {
				ArrayList<String> mediaEntries = med.toList();
				for (String s : mediaEntries) {
					pw.println(s);
				}
			}
		}
	}
	
	public ArrayList<Media> getMediaList() {
		return mediaList;
	}
}
