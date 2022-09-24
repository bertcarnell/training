/**
 * Cat
 * @author bertc
 *
 */
public class Cat extends Pet {
	
	/**
	 * Cat
	 * @param name type of cat
	 * @param price price of cat
	 * @param foodPerDay food required for cat
	 */
	public Cat(String name, float price, float foodPerDay) {
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
