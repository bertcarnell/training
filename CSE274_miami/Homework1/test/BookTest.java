import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BookTest {

	@Test
	void test() throws Exception {
		Book med = new Book();
		assertEquals(0, med.getUniqueID());
		assertEquals("", med.getTitle());
		assertEquals("book", med.getType());
		assertEquals("", med.getAuthor());
		assertEquals("", med.getPublisher());
		
		Book med2 = new Book("A", 56, "B", "C");
		assertEquals(56, med2.getUniqueID());
		assertEquals("A", med2.getTitle());
		assertEquals("book", med2.getType());
		assertEquals("B", med2.getAuthor());
		assertEquals("C", med2.getPublisher());
	}

}
