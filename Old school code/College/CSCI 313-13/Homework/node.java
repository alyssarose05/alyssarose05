/**
 * This node class is meant to be used with the queue class so that it may use a linked implementation.
 * @author Alyssa Ayala, CSCI 313-13
 * Due Date: October 25th, 2021 @ 9:00AM
 */
public class node {
	protected int data; // Attributes: the integer and the next node
	protected node next;
	
	public node(int data) {
		this.data = data; // data -> null
		this.next = null;
	}
}