import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoublyLinkedListTest {

	@Test
	void testDoublyLinkedList() {
		DoublyLinkedList<Integer> ints = new DoublyLinkedList<Integer>();
		assertEquals(0, ints.size());
	}

	@Test
	void testAdd() {
		DoublyLinkedList<Integer> ints = new DoublyLinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			assertTrue(ints.add(i));
		}
		assertEquals(10, ints.size());
	}

	@Test
	void testRemove() {
		DoublyLinkedList<Integer> ints = new DoublyLinkedList<Integer>();
		Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {ints.remove();});
		
		ints.add(50);
		ints.remove();
		assertEquals(0, ints.size());
		ints.clear();
		
		for (int i = 0; i < 10; i++) {
			assertTrue(ints.add(i));
		}
		assertEquals(10, ints.size());
		ints.remove();
		assertEquals(9, ints.size());
		ints.remove();
		assertEquals(8, ints.size());
	}

	@Test
	void testClear() {
		DoublyLinkedList<Integer> ints = new DoublyLinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			assertTrue(ints.add(i));
		}
		assertEquals(10, ints.size());
		ints.clear();
		assertEquals(0, ints.size());
	}

	@Test
	void testSort() {
		int[] unsorted = {7, 2, 5, 9, 3, 6, 1, 8, 0, 4};
		DoublyLinkedList<Integer> ints = new DoublyLinkedList<Integer>();
		for (int i = 0; i < unsorted.length; i++) {
			assertTrue(ints.add(unsorted[i]));
		}
		assertEquals(unsorted.length, ints.size());
		ints.sort();
		String expected = "0 1 2 3 4 5 6 7 8 9";
		assertTrue(ints.toString().equals(expected));
		
		ints.clear();
		ints.sort();
		
		ints.add(6);
		ints.sort();
		assertTrue(ints.toString().equals("6"));
		
		ints.add(3);
		ints.sort();
		assertTrue(ints.toString().equals("3 6"));

		ints.add(1);
		ints.sort();
		assertTrue(ints.toString().equals("1 3 6"));
	}

	@Test
	void testToString() {
		DoublyLinkedList<Integer> ints = new DoublyLinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			assertTrue(ints.add(i));
		}
		assertEquals(10, ints.size());
		String expected = "0 1 2 3 4 5 6 7 8 9";
		assertTrue(ints.toString().equals(expected));
	}
	
	@Test
	void testToStringBackwards() {
		int[] unsorted = {7, 2, 5, 9, 3, 6, 1, 8, 0, 4};
		DoublyLinkedList<Integer> ints = new DoublyLinkedList<Integer>();
		for (int i = 0; i < unsorted.length; i++) {
			assertTrue(ints.add(unsorted[i]));
		}
		assertEquals(unsorted.length, ints.size());

		String expected = "7 2 5 9 3 6 1 8 0 4";
		assertTrue(ints.toString().equals(expected));
		
		expected = "4 0 8 1 6 3 9 5 2 7";
		assertTrue(ints.toStringBackwards().equals(expected));
		
		ints.sort();
		
		expected = "0 1 2 3 4 5 6 7 8 9";
		assertTrue(ints.toString().equals(expected));
		
		expected = "9 8 7 6 5 4 3 2 1 0";
		assertTrue(ints.toStringBackwards().equals(expected));
	}
}
