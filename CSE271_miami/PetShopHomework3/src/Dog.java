/**
 * Dog
 * @author bertc
 *
 */
public class Dog extends Pet {
	/**
	 * Dog
	 * @param name type of dog
	 * @param price price of dog
	 * @param foodPerDay food required per day for dog
	 */
	public Dog(String name, float price, float foodPerDay) {
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
		return false;
	}
}

