import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VideoTest {

	@Test
	void test() throws Exception {
		Video med = new Video();
		assertEquals(0, med.getUniqueID());
		assertEquals("", med.getTitle());
		assertEquals("video", med.getType());
		assertEquals("", med.getDirector());
		assertEquals("Digital", med.getFormat());
		
		Video med2 = new Video("A", 56, "B", "Blu-Ray");
		assertEquals(56, med2.getUniqueID());
		assertEquals("A", med2.getTitle());
		assertEquals("video", med2.getType());
		assertEquals("B", med2.getDirector());
		assertEquals("Blu-Ray", med2.getFormat());
	}

}
