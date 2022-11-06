import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Audio extends Media implements Serializable, Comparable<Audio> {
	private static final long serialVersionUID = 183859439L;
	private int billboardRank;
	private boolean operatic;
	private static String AUDIO_FORMAT = "Audio\t%s\t%s\t%d\t%.2f\t%d\t%b";
	
	public Audio(String upc, String title, int year) {
		super(upc, title, year);
	}
	
	public int getBillboardRank() {
		return billboardRank;
	}
	
	public void setBillboardRank(int rank) {
		billboardRank = rank;
	}
	
	public boolean isOperatic() {
		return operatic;
	}
	
	public void setOperatic(boolean opera) {
		operatic = opera;
	}
	
	public int compareTo(Audio o) {
		if (billboardRank != o.getBillboardRank()) return (int) Math.signum(billboardRank - o.getBillboardRank());
		if (getPrice() != o.getPrice()) return (int) Math.signum(getPrice() - o.getPrice());
		return (getTitle().compareTo(o.getTitle()));
	}
	
	public String toString() {
		return String.format(AUDIO_FORMAT, getUpc(), getTitle(), getYear(), getPrice(), billboardRank, operatic);
	}
	
	public static Audio load(String info) {
		ArrayList<String> infoResult = new ArrayList<String>();
		try (Scanner sc = new Scanner(info)) {
			sc.useDelimiter("\t");
			while (sc.hasNext()) {
				infoResult.add(sc.next());
			}
		}
		Audio result = new Audio(infoResult.get(1), infoResult.get(2), Integer.parseInt(infoResult.get(3)));
		result.setPrice(Float.parseFloat(infoResult.get(4)));
		result.setBillboardRank(Integer.parseInt(infoResult.get(5)));
		result.setOperatic(Boolean.parseBoolean(infoResult.get(6)));
		
		return result;
	}
}
