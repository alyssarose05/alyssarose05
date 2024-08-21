/**
 * Programming Exercises Homework: Stack
 * This class creates a stack using an array implementation. 
 * The following methods are included to accomplish this: one constructor, isEmpty, isFull, push, peek, pop, and search.
 * @author Alyssa Ayala, CSCI 313-13
 * Due Date: October 25th, 2021 @ 9:00AM
 */
public class stack {
	private int[] data; // Attributes: a stack and its top
	private int top; 
	
	/**
	 * A default constructor that creates an empty stack.
	 */
	public stack(int size) {
		this.top = -1; // The array size is unchangeable, but the top makes the stack flexible in size.
		this.data = new int[size];
	}
	
	/**
	 * Checks to see if the stack is empty or not.
	 * @return 1 if the stack is empty, and 0 otherwise.
	 */
	public boolean isEmpty() {
		return (this.top == -1); // Meaning there is nothing in the stack.
	}
	
	/**
	 * Checks to see if the stack is full or not.
	 * @return 1 if the stack is full, and 0 otherwise.
	 */
	public boolean isFull() {
		return (this.top == data.length); // Unlike a linked implementation, the array has a specific size that cannot be changed. As such, a stack can be full unlike a queue, which doesn't have a specific size.
	}
	
	/**
	 * Pushes an integer into the stack.
	 * @param x The integer to be pushed into the stack.
	 */
	public void push(int x) {
		if (this.isFull()) // No integers can be pushed into a full stack as there is no room for them.
			throw new IllegalArgumentException("Full stack.");
		this.top += 1; // Increase the stack length and apply x to it.
		this.data[this.top] = x;
	}
	
	/**
	 * Gets the top element of the stack.
	 * @return The top element of the stack.
	 */
	public int peek() {
		return this.top; // Return this.top as the output.
	}
	
	/**
	 * Pops the top integer out of the stack.
	 * @return The old top before it was popped out of the stack.
	 */
	public int pop() {
		if (this.isEmpty()) // No integers can be popped out of an empty stack as there are none.
			throw new IllegalArgumentException("Empty stack.");
		int oldTop = this.top;
		this.top -= 1; // Decrease the stack length by 1. No elements are actually removed from the data array, but they can be changed the next time push() is used.
		return oldTop;
	}
	
	/**
	 * Searches the stack for a specific integer.
	 * @param x The integer to search the stack for
	 * @return true if the integer is in the stack, and false otherwise.
	 */
	public boolean search(int x) {
		for (int i = 0; i <= this.top; i++) // A for-loop to search for x in the data array up to this.top
			if (this.data[i] == x) 
				return true;
		return false;
	}
}