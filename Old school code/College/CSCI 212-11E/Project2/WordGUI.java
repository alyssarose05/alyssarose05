import javax.swing.JFrame;
import javax.swing.JTextArea; 
import javax.swing.JScrollPane;
import java.awt.GridLayout;

/** 
 * The class that has the properties of the JFrame with an unsorted WordList and a sorted WordList.
 * Due Date: November 2nd, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 1
 */
public class WordGUI {
	
	/**
	 * A two-argument constructor that directly runs the method responsible for creating the JFrame: createAndShow().
	 * @param uwl the unsorted WordList
	 * @param swl the sorted WordList
	 */
	public WordGUI(UnsortedWordList uwl, SortedWordList swl) {
		createAndShow(uwl, swl);
	}
	
	/**
	 * Creates and shows the WordGUI object with one row and two columns.
	 * @param uwl the WordList in the first column
	 * @param swl the WordList in the last column
	 */
	public void createAndShow(UnsortedWordList uwl, SortedWordList swl) {
		JFrame myFrame = new JFrame("Project2");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(400, 400);
		myFrame.setLocation(550, 550);
		myFrame.setLayout(new GridLayout(1, 2)); //1 column for unsorted, and another column for sorted
		myFrame.setTitle("Project2");
		
		JTextArea textArea1 = new JTextArea(25, 25);
		textArea1.setEditable(false); 
		JScrollPane scrollPane1 = new JScrollPane(textArea1);
		myFrame.getContentPane().add(scrollPane1);
		
		JTextArea textArea2 = new JTextArea(25, 25);
		textArea2.setEditable(false);
		JScrollPane scrollPane2 = new JScrollPane(textArea2);
		myFrame.getContentPane().add(scrollPane2);
		
		myFrame.pack();
		myFrame.setVisible(true);
		
		textArea1.append("Unsorted Words:\n");
		textArea1.append(String.valueOf(uwl)); //The WordList must be converted into a String to be appended.
		
		textArea2.append("Sorted Words:\n");
		textArea2.append(String.valueOf(swl)); //The WordList must be converted into a String to be appended.

	}
}
