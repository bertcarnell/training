/**
 * Octopus
 * @author bertc
 *
 */
public class Octopus extends Pet {
	/**
	 * Octopus
	 * @param name type of Octopus
	 * @param price price of Octopus
	 * @param foodPerDay food required per day for Octopus
	 */
	public Octopus(String name, float price, float foodPerDay) {
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
