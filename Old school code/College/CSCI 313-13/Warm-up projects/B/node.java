/**
 * A node class that is used to create a linked-structure for the polynomial class.
 * @author Alyssa Ayala, CSCI 313-13
 * Due Date: October 13th, 2021 @ 9:00AM
 */
public class node {
	protected term term;
	protected node next;
	
	/**
	 * A constructor that sets up a node with one term as the head and the next one as null.
	 * @param term The term to be added to the node.
	 */
	public node(term term) { 
		this.term = term; // term -> null
		this.next = null; 
	}
}