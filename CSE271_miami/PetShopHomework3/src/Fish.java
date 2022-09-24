/**
 * Fish
 * @author bertc
 *
 */
public class Fish extends Pet {

	/**
	 * Fish
	 * @param name type of fish
	 * @param price price of fish
	 * @param foodPerDay food required for fish per day
	 */
	public Fish(String name, float price, float foodPerDay) {
		super(name, price, foodPerDay);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getKind() {
		return this.getClass().getName().concat(": ").concat(super.kind);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("%s\t%s\t%.2f\t%.2f", this.getClass().getName(), super.kind, getPrice(), getFoodPerDay());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAquatic() {
		return true;
	}
}
