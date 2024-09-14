
/**
 * An interface that must be implemented by Generic Linked List in Homework 2
 * 
 * @author john1819
 *
 */
public interface List<T extends Comparable<T>> {

	/**
	 * Adds a specified data value to this list
	 * 
	 * @param value data element to be added
	 * @return true if the add was successful, false otherwise
	 */
	public boolean add(T value);

	/**
	 * Removes an element from the front of a list. If the list is not empty, return
	 * the valuethat is removed. If the list is empty, throw an
	 * OutOfBoundsException.
	 * 
	 * @return the word at the specified index
	 * @throws IndexOutOfBoundsException if list is empty
	 */
	public T remove();

	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return the number of elements in this list
	 */
	public int size();

	/**
	 * Removes everything from the list.
	 */
	public void clear();

	/*
	 * Returns a space-separated list of the elements in the list. If the list
	 * contains no elements, return an empty string ""
	 */
	public String toString();
	
	/**
	 * This method will sort the LinkedList based on the results of 
	 * the compareTo method.  The sort algorithm is programmers choice.
	 * It modifies the LinkedList so the first element is the lowest value and
	 * the last element is the highest value
	 */
	public void sort();

	// A  Node class. By making it an inner class,
	// the LinkedList class methods can access but other classes can not
	class Node<S> {
		protected S data;
		protected Node<S> next;
		protected Node<S> prev;

		// Constructs a new node with the specified data
		protected Node(S data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

}
