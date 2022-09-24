import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class TestPetShop {

	@Test
	void testProcessLine() {
		PetShop ps = new PetShop();
		
		ps.processLine("Fish	Goldfish	4.75	0.02");
		ArrayList<Thing> alt = ps.getAllThings();
		Assertions.assertEquals(1, alt.size());
		Assertions.assertEquals("Fish", alt.get(0).getClass().getName());

		ps.processLine("WormCan	5.75	1.0");
		Assertions.assertEquals(2, alt.size());
		Assertions.assertEquals("Fish", alt.get(0).getClass().getName());
		Assertions.assertEquals("WormCan", alt.get(1).getClass().getName());
	}
}
