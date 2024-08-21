/** 
 * An abstract class that creates a list with a set of Words.
 * Due date: November 2nd, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 1
 */
public abstract class WordList {
	protected WordNode first;
	protected WordNode last;
	protected int length;
	
	/** 
	 * The no-argument constructor for an empty WordList: a null first node, the last node being equal to the first, and a length of 0.
	 */
	public WordList() {
		first = new WordNode(null); 
		last = first;
		length = 0;
	}
	
	/**
	 * The append method; adds a Word to the end of the WordList.
	 * @param w the word to be added to the end of the WordList
	 */
	public void append(Word w) {
		WordNode newNode = new WordNode(w);
		last.next = newNode;
		last = newNode;
		length++;
	}
	
	/**
	 * A toString() method that prints a WordList as a String.
	 * @return a WordList with each Word having its own line
	 */
	public String toString() {
		WordNode p = first.next;
		String returnString = "";
		while (p != null) {
			returnString += p.data + "\n"; //We want the WordList to be printed as each word having its own line and another word below it.
			p = p.next;
		}
		return returnString;
	}
}
