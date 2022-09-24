
/**
 * Food
 * @author bertc
 *
 */
public abstract class Food extends Thing {
	private float price;
	private float weight;
	
	/**
	 * Food
	 * @param price price of food
	 * @param weight weight of food
	 */
	public Food(float price, float weight) {
		this.price = price;
		this.weight = weight;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getPrice() {
		return price;
	}
	
	/**
	 * get the weight
	 * @return the weight
	 */
	public float getWeight() {
		return weight;
	}
	
	/**
	 * Test equality
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Food)) {
			return false;
		}
		Food myo = (Food) o;
		return this.getClass().getName().equals(myo.getClass().getName()) &
				this.getPrice() == myo.getPrice() &
				this.getWeight() == myo.getWeight();
	}
}
