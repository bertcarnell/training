import java.util.ArrayList;

public class Video  extends Media {
	private String director;
	private String format;
	
	public Video() throws Exception {
		super("", 0, "video");
		director = "";
		format = "Digital";
		checkFormat();
	}
	
	public Video(String title, int uniqueID, String director, String format) throws Exception {
		super(title, uniqueID, "video");
		this.director = director;
		this.format = format;
		checkFormat();
	}
	
	public String getDirector() {
		return director;
	}
	
	public String getFormat() {
		return format;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public void setFormat(String format) throws Exception {
		this.format = format;
		checkFormat();
	}
	
	private void checkFormat() throws Exception {
		if (!format.equalsIgnoreCase("Digital") & !format.equalsIgnoreCase("DVD") & !format.equalsIgnoreCase("Blu-Ray"))
			throw new Exception("ERROR: Format must be Digital, DVD, or Blu-Ray");
	}
	
	@Override
	public void printMedia() {
		System.out.println(this.toString());
	}
	
	@Override
	public String toString() {
		return "Video [director=" + director + ", format=" + format + 
				", title=" + this.getTitle() + ", uniqueID=" + this.getUniqueID() + ", type=video]";
	}
	
	@Override
	public ArrayList<String> toList() {
		ArrayList<String> out = new ArrayList<String>(5);
		out.add(String.valueOf(this.getUniqueID()));
		out.add(this.getTitle());
		out.add("video");
		out.add(this.getDirector());
		out.add(this.getFormat());
		return out;
	}
}
