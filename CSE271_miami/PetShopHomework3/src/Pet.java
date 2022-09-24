/**
 * A Pet
 * @author bertc
 *
 */
public abstract class Pet extends Thing {
	protected String kind;
	private float price;
	private float foodPerDay;
	
	/**
	 * Create a pet
	 * @param name the kind of pet
	 * @param price the pet price
	 * @param foodPerDay the amount of required food per day
	 */
	public Pet(String name, float price, float foodPerDay) {
		this.price = price;
		this.foodPerDay = foodPerDay;
		this.kind = name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getPrice() {
		return price;
	}
	
	/**
	 * get the amount of food required by the pet per day
	 * @return
	 */
	public float getFoodPerDay() {
		return foodPerDay;
	}
	
	/**
	 * Test for equality
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Pet)) {
			return false;
		}
		Pet myo = (Pet) o;
		return this.getKind().equals(myo.getKind());
	}
}
