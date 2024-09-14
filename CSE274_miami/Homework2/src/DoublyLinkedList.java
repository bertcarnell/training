import java.util.ArrayList;

public class DoublyLinkedList<T extends Comparable<T>> implements List<T> {
	private List.Node<T> headNode;
	private int length = 0;
	
	public DoublyLinkedList() {
		headNode = null;
	}

	@Override
	public boolean add(T value) {
		List.Node<T> newNode = new List.Node<T>(value);
		
		// if this is the head node
		if (headNode == null) {
			headNode = newNode;
		}
		else if (length == 1) {
			newNode.prev = headNode;
			headNode.next = newNode;
		}
		// else move to the end of the list
		else {
			// start a new temp node at the head
			List.Node<T> tempNode = headNode;
			// move down the list until you reach the end
			while (tempNode.next != null) {
				tempNode = tempNode.next;
			}
			
			// end of list
			newNode.prev = tempNode;
			tempNode.next = newNode;
		}
		length++;
		
		return true;
	}

	@Override
	public T remove() {
		// remove from the font
		if (headNode == null) {
			throw new ArrayIndexOutOfBoundsException("Attempted to remove from an empty list");
		}
		T returnVal = headNode.data;
		if (length == 1) {
			headNode = null;
			length = 0;
		}
		else {
			headNode = headNode.next;
			headNode.prev = null;
			length--;
		}
		return returnVal;
    }

	@Override
	public int size() {
		return length;
	}

	@Override
	public void clear() {
		headNode = null;
		length = 0;
	}

	@Override
	public void sort() {
		headNode = mergeSort(headNode);
		
		if (headNode == null) {
			return;
		}
		headNode.prev = null;
		if (length == 1) {
			return;
		}

		// traverse the list and fix all the reverse pointers
		List.Node<T> tempNode;
		List.Node<T> prevTempNode;
				
		tempNode = headNode.next;
		prevTempNode = headNode;
		
		tempNode.prev = prevTempNode;
		
		while (tempNode.next != null) {
			tempNode = tempNode.next;
			prevTempNode = prevTempNode.next;
			tempNode.prev = prevTempNode;
		}
	}
	
	private List.Node<T> mergeSort(List.Node<T> node) {
		// if the node is empty or there is only one element, return
		if (node == null || node.next == null) {
			return node;
		}
		
		// Split the list
		ArrayList<List.Node<T>> AB = splitList(node);
		List.Node<T> A = mergeSort(AB.get(0));
		List.Node<T> B = mergeSort(AB.get(1));
		
		return joinLinkedLists(A, B);
	}

    // merging two linked list
	private List.Node<T> joinLinkedLists(List.Node<T> A, List.Node<T> B)
	{
		List.Node<T> ret;
		
		if (A == null) {
			return B;
		}
		else if (B == null) {
			return A;
		}

		if (A.data.compareTo(B.data) <= 0)
		{
			ret = A;
			ret.next = joinLinkedLists(A.next, B);
		}
		else
		{
			ret = B;
			ret.next = joinLinkedLists(A, B.next);
		}

		return ret;
	}

    // splitting list
	private ArrayList<Node<T>> splitList(List.Node<T> node)
	{
		ArrayList<List.Node<T>> ret = new ArrayList<List.Node<T>>();
		if (node == null || node.next == null) {
			ret.add(node);
			ret.add(null);
			return(ret);
		}

		List.Node<T> backward = node;
		List.Node<T> forward = node.next;

		// Forward moves twice and backward moves once
		while (forward != null) {
			forward = forward.next;
			if (forward != null) {
				backward = backward.next;
				forward = forward.next;
			}
		}

		ret.add(node);
		ret.add(backward.next);
		backward.next = null;
		
		return ret;
	}

	@Override
	public String toString() {
		return nodeToString(headNode);
	}
	
	public String nodeToString(List.Node<T> node) {
		String retString = "";
		if (node == null) {
			return retString;
		}
		// else move to the end of the list
		else {
			// start a new temp node at the head
			List.Node<T> tempNode = node;
			
			// move down the list until you reach the end
			while (tempNode.next != null) {
				retString += tempNode.data.toString() + " ";
				tempNode = tempNode.next;
			}
			retString += tempNode.data.toString();
		}
		return retString;
	}
	
	public void print() {
		if (headNode == null) {
			return;
		}
		List.Node<T> tempNode = headNode;
		while (tempNode.next != null) {
			System.out.println(tempNode.data.toString());
			tempNode = tempNode.next;
		}
		System.out.println(tempNode.data.toString());
	}

	public String toStringBackwards() {
		String retString = "";
		// Traverse to the end
		if (headNode == null) {
			return retString;
		}
		if (length == 1) {
			return headNode.data.toString();
		}

		List.Node<T> tempNode = headNode;
	
		while (tempNode.next != null) {
			tempNode = tempNode.next;
		}
		
		while(tempNode.prev != null) {
			retString += tempNode.data.toString() + " ";
			tempNode = tempNode.prev;
		}
		
		retString += tempNode.data.toString();
		
		return retString;
	}

	public void printBackwards() {
		if (headNode == null) {
			return;
		}
		List.Node<T> tempNode = headNode;
		while (tempNode.next != null) {
			tempNode = tempNode.next;
		}
		while(tempNode.prev != null) {
			System.out.println(tempNode.data.toString());
			tempNode = tempNode.prev;
		}
		System.out.println(tempNode.data.toString());
	}
}
