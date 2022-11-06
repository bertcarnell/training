import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Video extends Media implements Serializable, Comparable<Video> {
	private static final long serialVersionUID = 1292929L;
	private String mpaa;
	private boolean hdcp;
	private static String VIDEO_FORMAT = "Video\t%s\t%s\t%d\t%.2f\t%s\t%b";
	
	public Video(String upc, String title, int year) {
		super(upc, title, year);
	}
	
	public String getMpaa() {
		return mpaa;
	}
	
	public void setMpaa(String mpaa) {
		this.mpaa = mpaa;
	}
	
	public boolean isHdcp() {
		return hdcp;
	}
	
	public void setHdcp(boolean opera) {
		hdcp = opera;
	}
	
	public int compareTo(Video o) {
		if (mpaa != o.getMpaa()) return mpaa.compareTo(o.getMpaa());
		if (getPrice() != o.getPrice()) return (int) Math.signum(getPrice() - o.getPrice());
		return (getTitle().compareTo(o.getTitle()));
	}
	
	public String toString() {
		return String.format(VIDEO_FORMAT, getUpc(), getTitle(), getYear(), getPrice(), mpaa, hdcp);
	}
	
	public static Video load(String info) {
		ArrayList<String> infoResult = new ArrayList<String>();
		try (Scanner sc = new Scanner(info)) {
			sc.useDelimiter("\t");
			while (sc.hasNext()) {
				infoResult.add(sc.next());
			}
		}
		Video result = new Video(infoResult.get(1), infoResult.get(2), Integer.parseInt(infoResult.get(3)));
		result.setPrice(Float.parseFloat(infoResult.get(4)));
		result.setMpaa(infoResult.get(5));
		result.setHdcp(Boolean.parseBoolean(infoResult.get(6)));
		
		return result;
	}

}
