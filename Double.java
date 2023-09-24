/*****************************************************************
 * filename: Double.java
 *
 * Implement a doubly linked ring that stores char values.
 * The main() method creates a ring, manipulates it, and prints it.
 ****************************************************************/


/** class DoubleRing
 *
 * class DoubleRing implements an actual doubly linked ring.
 * The ring has the concept of a "current" element.
 * When the user adds to or removes ring elements,
 * the operations are done at the "current" position.
 *
 * There are several methods below that must be implemented by the student.
 */
class DoubleRing {
	/** class Node - a member class of DoubleRing
	 * class Node represents one element in a doubly linked ring.
	 * It stores a char value and has references ('prev' and 'next')
	 * to other Nodes in a ring.
	 */
	static int counter=0;// this is a counter that keeps track of how many elements are in the linked list
	private class Node {
		private final char m_val;
		Node prev;
		Node next;

		Node(char c) { 
			++counter;// adds one every time an node is created
			m_val = c; 
			prev = null; 
			next = null; 
		}
		public char val() { 
			return m_val; 
		}
	}

////
//// Class member variables
////
	// Node that tracks the 'current' i.e. currently-selected Node in this ring.
	private Node m_current;

////
//// Class methods
////
	DoubleRing () { 
		m_current = null; 
	}

	// Return current node's value
	char current() {
		if (m_current == null) {
			return (char) 0;
		}
		return m_current.val();
	}

	// Advance 'current' to point to the following node.
	//
	// If next() is called repeatedly, current will eventually
	// wrap all the way around the ring.
	void next() {
		if (m_current != null) {
			m_current = m_current.next;
		}
	}
	
	// add()
	//
	// Insert 'n' into this ring, after current and before current.next .
	// Leave m_current pointing to the same node as before the
	// call to add().
	//
	// Be sure to handle if the ring is empty.
	void add(Node n){
		if(m_current==null) {// this handles if the ring is empty
			m_current=n;
			m_current.next=n;
			m_current.prev=n;
		//this handles the regular adding of an element to the list
		}else if(m_current!=null&&m_current.next!=null && m_current.prev!=null) { 
			n.next=m_current.next;
			n.next.prev=n;
			n.prev=m_current;
			m_current.next=n;
		}
		
		// STUDENTS COMPLETE THIS METHOD.
	}

	// Helper function so we can add chars rather than Nodes to this ring.
	void add(char c) {
		add(new Node(c));	
	}

	// remove()
	//
	// Remove current node.
	// Afterwards m_current should point to the successor of the old 'current'.
	//
	// Be sure to handle cases of:
	//	only 1 element left in the ring - make the ring empty
	//	ring is entirely empty - throw an IndexOutOfBoundsException
	void remove()throws IndexOutOfBoundsException {
		// STUDENTS COMPLETE THIS METHOD.
		if (m_current==null && m_current.next==null && m_current.prev==null) {// if the ring is empty it throws the IndexOutOfBoundsException
			throw new IndexOutOfBoundsException();
		}
		else if (m_current.next==m_current && m_current.prev==m_current) {// if the ring only has one element then it makes it empty
			m_current=null; 
			m_current.next=null ;
			m_current.prev=null;
			
		}
		else {//handles the regular case where the list has more than 1 element
			counter --;// substracts one to the counter by one, keeping track of the amount of elements
			m_current.prev.next=m_current.next;
			m_current.next.prev=m_current.prev;
			m_current=m_current.next;
		}
		
	}
	
	// PrintRing()
	//
	// Print all char values in the ring in order,
	// starting from current.
	// Do not keep on printing forever!
	
	void PrintRing() {// prints the ring from the current element to the element before it
		// STUDENTS COMPLETE THIS METHOD.
		//m_current=m_current.next;
		for (int i = 0;i<counter;i++) {
			
			System.out.println(current());
			m_current=m_current.next;
		}
	}
} // end class DoubleRing


/** class Double
 *
 * This class implements the program's main() function, which exercises class DoubleRing
 * and generates output.
 */
public class Double {

	public static void main(String[] args) {
		// Create an empty doubly linked ring.
		DoubleRing ring = new DoubleRing();

		// Put the letters 'a' through 'z' into the ring.
		for(char c = 'a'; c <= 'z'; c++) {
			ring.add(c);
			ring.next();
		}
	

		// Go through a sequence of ring operations.
		// These should not cause any errors.
		ring.add('!');
		ring.remove();

		ring.next();
		ring.remove();

		ring.next();
		ring.remove();

		ring.add('@');
		ring.next();
		ring.next();

		ring.add('#');
		ring.remove();
		ring.next();
		ring.add('$');
		ring.next();
		ring.remove();
		ring.next();
		ring.next();
		ring.next();
		ring.next();
		ring.next();
		ring.next();
		ring.remove();
		ring.next();
		ring.next();
		
		// Output the final ring.
		ring.PrintRing();
	} // end main()

} // end class Double