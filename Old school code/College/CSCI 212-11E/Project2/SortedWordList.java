/**
 * A class containing the sorted version of a WordList.
 * Due Date: November 2nd, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 1
 */
public class SortedWordList extends WordList {	
	
	/**
	 * A no-argument constructor with the properties of the abstract WordList class.
	 */
	public SortedWordList() {
		super();
	}
	
	/**
	 * Adds a Word to its appropriate place to keep the WordList sorted.
	 * @param w the Word to be added to the SortedWordList
	 */
	public void add(Word w) {
		
		WordNode firstNode = first; //Needed to compare both the previous WordNode and the next WordNode
		WordNode nextNode = firstNode.next;
		WordNode newNode = new WordNode(w);

		while (nextNode != null && nextNode.data.compareTo(newNode.data) < 0) { 
			firstNode = firstNode.next; //Makes these WordNodes equal to the next node to swap.
			nextNode = nextNode.next; 
		}
		
		newNode.next = nextNode; //Makes these WordNodes equal to the previous node to swap.
		firstNode.next = newNode;
	}
}