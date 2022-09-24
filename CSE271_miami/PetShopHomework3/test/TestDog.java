import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestDog {

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void testDog() {
		Dog dog = new Dog("Vizla", 1.0f, 2.0f);
		Assertions.assertEquals(1.0f, dog.getPrice());
		Assertions.assertEquals(2.0f, dog.getFoodPerDay());
		Assertions.assertEquals("Dog: Vizla", dog.getKind());
		Assertions.assertFalse(dog.isAquatic());
		Assertions.assertEquals("Dog\tVizla\t1.00\t2.00", dog.toString());
		
		Dog dog2 = new Dog("Vizla", 0.0f, 0.0f);
		Assertions.assertTrue(dog.equals(dog2));
		Assertions.assertEquals(dog, dog2);
		
		Dog dog3 = new Dog("Poodle", 1.0f, 2.0f);
		Assertions.assertFalse(dog.equals(dog3));
		
		Cat cat = new Cat("Bad", 1.0f, 2.0f);
		Assertions.assertFalse(dog.equals(cat));
	}

}
