import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestPet {

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void testSubClasses() {
		Dog dog = new Dog("Vizla", 1.0f, 2.0f);
		Cat cat = new Cat("Bad", 6.0f, 7.0f);
		Fish fish = new Fish("Floppy", 3.0f, 4.0f);
		Octopus oct = new Octopus("Arms", 1.1f, 2.2f);
		
		Assertions.assertEquals(1.0f, dog.getPrice());
		Assertions.assertEquals(2.0f, dog.getFoodPerDay());
		Assertions.assertEquals("Dog: Vizla", dog.getKind());
		Assertions.assertFalse(dog.isAquatic());
		Assertions.assertEquals("Dog\tVizla\t1.00\t2.00", dog.toString());

		Assertions.assertEquals(6.0f, cat.getPrice());
		Assertions.assertEquals(7.0f, cat.getFoodPerDay());
		Assertions.assertEquals("Cat: Bad", cat.getKind());
		Assertions.assertFalse(cat.isAquatic());
		Assertions.assertEquals("Cat\tBad\t6.00\t7.00", cat.toString());

		Assertions.assertEquals(3.0f, fish.getPrice());
		Assertions.assertEquals(4.0f, fish.getFoodPerDay());
		Assertions.assertEquals("Fish: Floppy", fish.getKind());
		Assertions.assertTrue(fish.isAquatic());
		Assertions.assertEquals("Fish\tFloppy\t3.00\t4.00", fish.toString());

		Assertions.assertEquals(1.1f, oct.getPrice());
		Assertions.assertEquals(2.2f, oct.getFoodPerDay());
		Assertions.assertEquals("Octopus: Arms", oct.getKind());
		Assertions.assertTrue(oct.isAquatic());
		Assertions.assertEquals("Octopus\tArms\t1.10\t2.20", oct.toString());
		
		Dog dog2 = new Dog("Vizla", 0.0f, 0.0f);
		Assertions.assertTrue(dog.equals(dog2));
		Assertions.assertEquals(dog, dog2);
		
		Dog dog3 = new Dog("Poodle", 1.0f, 2.0f);
		Assertions.assertFalse(dog.equals(dog3));
		
		Assertions.assertFalse(dog.equals(cat));

		Cat cat2 = new Cat("Bad", 0.0f, 0.0f);
		Assertions.assertTrue(cat.equals(cat2));
		Assertions.assertEquals(cat, cat2);
		
		Cat cat3 = new Cat("Poodle", 1.0f, 2.0f);
		Assertions.assertFalse(cat.equals(cat3));
		
		Assertions.assertFalse(cat.equals(dog));
	}

	@Test
	void testPet() {
		Pet pet = new Dog("A", 20.0f, 30.0f);
		Assertions.assertEquals("Dog", pet.getClass().getName());
		
		Assertions.assertEquals(20.0f, pet.getPrice());
		Assertions.assertEquals(30.0f, pet.getFoodPerDay());
		Assertions.assertEquals("Dog: A", pet.getKind());
		Assertions.assertFalse(pet.isAquatic());
		Assertions.assertEquals("Dog\tA\t20.00\t30.00", pet.toString());
	}

	@Test
	void testEqualsObject() {
		Pet pet = new Dog("A", 20.0f, 30.0f);
		Pet dog2 = new Dog("A", 0.0f, 0.0f);
		Assertions.assertEquals(pet, dog2);
		
		Dog dog3 = new Dog("Poodle", 1.0f, 2.0f);
		Assertions.assertFalse(pet.equals(dog3));
	}

	@Test
	void testThing() {
		Thing pet = new Dog("A", 20.0f, 30.0f);
		Assertions.assertFalse(pet.isAquatic());
	}
}
