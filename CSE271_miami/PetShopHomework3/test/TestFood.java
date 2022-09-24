import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestFood {

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void testSubClasses() {
		WormCan wc = new WormCan(1.0f, 2.0f);
		ChowBag cb = new ChowBag(6.0f, 7.0f);
		
		Assertions.assertEquals(1.0f, wc.getPrice());
		Assertions.assertEquals(2.0f, wc.getWeight());
		Assertions.assertEquals("WormCan", wc.getKind());
		Assertions.assertTrue(wc.isAquatic());
		Assertions.assertEquals("WormCan\t1.00\t2.00", wc.toString());

		Assertions.assertEquals(6.0f, cb.getPrice());
		Assertions.assertEquals(7.0f, cb.getWeight());
		Assertions.assertEquals("ChowBag", cb.getKind());
		Assertions.assertFalse(cb.isAquatic());
		Assertions.assertEquals("ChowBag\t6.00\t7.00", cb.toString());

		WormCan wc2 = new WormCan(1.0f, 2.0f);
		Assertions.assertTrue(wc.equals(wc2));
		Assertions.assertEquals(wc, wc2);
		
		WormCan wc3 = new WormCan(10.0f, 20.0f);
		Assertions.assertFalse(wc.equals(wc3));
		
		Assertions.assertFalse(wc.equals(cb));
	}

	@Test
	void testPet() {
		Food foo = new ChowBag(20.0f, 30.0f);
		Assertions.assertEquals("ChowBag", foo.getClass().getName());
		
		Assertions.assertEquals(20.0f, foo.getPrice());
		Assertions.assertEquals(30.0f, foo.getWeight());
		Assertions.assertEquals("ChowBag", foo.getKind());
		Assertions.assertFalse(foo.isAquatic());
		Assertions.assertEquals("ChowBag\t20.00\t30.00", foo.toString());
	}

	@Test
	void testEqualsObject() {
		Food pet = new ChowBag(20.0f, 30.0f);
		Food dog2 = new ChowBag(0.0f, 0.0f);
		Assertions.assertFalse(pet.equals(dog2));
	}
}
