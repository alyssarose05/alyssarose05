/**
 * Programming Exercises Homework: Queue
 * This class creates a queue using a linked implementation.
 * The following methods are included to accomplish this: one constructor, isEmpty, enQ, peekFront, peekRear, deQ, and search.
 * @author Alyssa Ayala, CSCI 313-13
 * Due Date: October 25th, 2021 @ 9:00AM
 */
public class queue {
	private node front, rear; // Attributes: Two nodes called the front and rear.
	
	/**
	 * A default constructor that creates an empty queue.
	 */
	public queue() {
		this.front = this.rear = null; // Both the front and rear are null since there is nothing in the queue.
	}
	
	/**
	 * Checks to see if the queue is empty or not.
	 * @return 1 if the queue is empty, and 0 otherwise.
	 */
	public boolean isEmpty() {
		return (this.front == null); // or (this.rear == null). If either one is null, the queue is empty, but we only need to check one of them.
	}
	
	/**
	 * Places an integer into the queue.
	 * @param x The integer to placed into the queue.
	 */
	public void enQ(int x) {
		if (this.isEmpty()) // Change the front and rear if the queue is empty.
			this.front = this.rear = new node(x);
		else // Otherwise, only change the rear. 
			this.rear = this.rear.next = new node(x);
	}
	
	/**
	 * Gets the front integer of the queue.
	 * @return The front integer of the queue.
	 */
	public int peekFront() {
		return this.front.data; // Return the front integer as an output.
	}
	
	/**
	 * Gets the rear integer of the queue.
	 * @return The rear integer of the queue.
	 */
	public int peekRear() {
		return this.rear.data; // Return the rear integer as an output.
	}
	
	/**
	 * Removes the front integer from the queue.
	 * @return The old front integer before it was removed from the queue.
	 */
	public int deQ() {
		if (this.isEmpty()) // No nodes can be popped from the queue as there are none.
			throw new IllegalArgumentException("Empty queue.");
		else if (this.front == this.rear) // Then we are removing the last element, so the queue is now empty.
			this.front = this.rear = null;
		int oldFront = this.front.data; // What the front was before being removed from the queue.
		this.front = this.front.next; // Change front to be the node after it.
		return oldFront;
	}
	
	/**
	 * Searches the queue for a specific integer.
	 * @param x The integer to be searched for in the queue.
	 * @return true if the integer is in the queue, and false otherwise.
	 */
	public boolean search(int x) {
		node currFront = this.front;
		while (currFront != null) { // Go through the front node (without actually changing it because of the temporary variable, currFront), and compare the data to x
			if (currFront.data == x)
				return true;
			currFront = currFront.next;
		}
		return false;
	}
}