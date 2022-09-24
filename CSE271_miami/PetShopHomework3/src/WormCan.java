/**
 * WormCan
 * @author bertc
 *
 */
public class WormCan extends Food {
	/**
	 * WormCan Food
	 * @param price price of WormCan
	 * @param weight weight of WormCan
	 */
	public WormCan(float price, float weight) {
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
		return true;
	}
}
