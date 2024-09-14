import java.util.ArrayList;

public class Book extends Media {
	private String author;
	private String publisher;
	
	public Book() throws Exception {
		super("", 0, "book");
		author = "";
		publisher = "";
	}
	
	public Book(String title, int uniqueID, String author, String publisher) throws Exception {
		super(title, uniqueID, "book");
		this.author = author;
		this.publisher = publisher;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	@Override
	public void printMedia() {
		System.out.println("Book (" + this.getUniqueID() + "): " + this.getTitle() +
				".\t" + author + ", " + publisher);
	}
	
	@Override
	public ArrayList<String> toList() {
		ArrayList<String> out = new ArrayList<String>(5);
		out.add(String.valueOf(this.getUniqueID()));
		out.add(this.getTitle());
		out.add("book");
		out.add(this.getAuthor());
		out.add(this.getPublisher());
		return out;
	}
}
