/**
 * The class that creates WordNodes to be used to add words to a WordList.
 * Due Date: November 2nd, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 1
 */
public class WordNode {
	protected Word data;
	protected WordNode next;
	
	/** 
	 * A one-argument constructor that takes a Word and collects the data of it, then makes the WordList's next space null.
	 * @param w the entered word
	 */
	public WordNode(Word w) {
		data = w; //Keeps the data on Word w
		next = null; //Presumes the next WordNode to be null
	}
}
