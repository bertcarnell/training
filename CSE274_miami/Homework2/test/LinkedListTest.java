import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinkedListTest {

	@Test
	void testLinkedList() {
		LinkedList<Integer> ints = new LinkedList<Integer>();
		assertEquals(0, ints.size());
	}

	@Test
	void testAdd() {
		LinkedList<Integer> ints = new LinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			assertTrue(ints.add(i));
		}
		assertEquals(10, ints.size());
	}

	@Test
	void testRemove() {
		LinkedList<Integer> ints = new LinkedList<Integer>();
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
		LinkedList<Integer> ints = new LinkedList<Integer>();
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
		LinkedList<Integer> ints = new LinkedList<Integer>();
		for (int i = 0; i < unsorted.length; i++) {
			assertTrue(ints.add(unsorted[i]));
		}
		assertEquals(unsorted.length, ints.size());
		ints.sort();
		String expected = "0 1 2 3 4 5 6 7 8 9";
		assertTrue(ints.toString().equals(expected));
	}

	@Test
	void testToString() {
		LinkedList<Integer> ints = new LinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			assertTrue(ints.add(i));
		}
		assertEquals(10, ints.size());
		String expected = "0 1 2 3 4 5 6 7 8 9";
		assertTrue(ints.toString().equals(expected));
	}

}
