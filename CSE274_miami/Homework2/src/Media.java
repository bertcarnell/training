import java.util.ArrayList;

public class Media implements Comparable<Media> {
	private String title;
	private int uniqueID;
	private String type;
	
	public Media() throws Exception {
		title = "";
		uniqueID = 0;
		type = "book";
		checkType();
	}
	
	public Media(String title, int uniqueID, String type) throws Exception {
		this.title = title;
		this.uniqueID = uniqueID;
		this.type = type;
		checkType();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getUniqueID() {
		return uniqueID;
	}
	
	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) throws Exception {
		this.type = type;
		checkType();
	}
	
	private void checkType() throws Exception {
		if (!this.type.equalsIgnoreCase("book") & !this.type.equalsIgnoreCase("video"))
			throw new Exception("ERROR: type must be book or video");
	}
	
	public void printMedia() {
		System.out.println("Default Print Method");
	};
	
	public ArrayList<String> toList() {
		System.out.println("Default toList method");
		return new ArrayList<String>();
	}

	@Override
	public int compareTo(Media o) {
		return this.getUniqueID() - o.getUniqueID();
	}
	
	public String toString() {
		String ret = "[" + type + " " + uniqueID + "]";
		return ret;
	}
}
