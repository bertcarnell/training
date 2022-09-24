
/**
 * Things (Pets and Food)
 * @author bertc
 *
 */
public abstract class Thing {
	/**
	 * Constructor
	 */
	public Thing() {
	}
	
	/**
	 * get the kind of thing
	 * @return the kind
	 */
	public abstract String getKind();
	
	/**
	 * Is thing related to aquatic animals
	 * @return logical
	 */
	public abstract boolean isAquatic();
	
	/**
	 * The the price of the thing
	 * @return price
	 */
	public abstract float getPrice();
}
