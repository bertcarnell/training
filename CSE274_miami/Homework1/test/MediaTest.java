import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MediaTest {

	@Test
	void test() throws Exception {
		Media med = new Media();
		assertEquals(0, med.getUniqueID());
		assertEquals("", med.getTitle());
		assertEquals("book", med.getType());
		
		Media med2 = new Media("A", 56, "video");
		assertEquals(56, med2.getUniqueID());
		assertEquals("A", med2.getTitle());
		assertEquals("video", med2.getType());
	}

}
