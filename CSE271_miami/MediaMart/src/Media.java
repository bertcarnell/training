import java.io.Serializable;

public class Media implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1830989L;
	private String upc;
	private String title;
	private int year;
	private float price;
	
	public Media(String upc, String title, int year) {
		this(upc, title, year, 0.0f);
	}
	
	public Media(String upc, String title, int year, float price) {
		this.upc = upc;
		this.title = title;
		this.year = year;
	}
	
	public String getUpc() {
		return upc;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getYear() {
		return year;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
}
