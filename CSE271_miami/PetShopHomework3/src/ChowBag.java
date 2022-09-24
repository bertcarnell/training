/**
 * ChowBag
 * @author bertc
 *
 */
public class ChowBag extends Food {
	/**
	 * ChowBag
	 * @param price of food
	 * @param weight of food
	 */
	public ChowBag(float price, float weight) {
		super(price, weight);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getKind() {
		return this.getClass().getName();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("%s\t%.2f\t%.2f", this.getClass().getName(), getPrice(), getWeight());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAquatic() {
		return false;
	}
}
