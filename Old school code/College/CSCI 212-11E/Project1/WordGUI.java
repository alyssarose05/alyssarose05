import javax.swing.JFrame;
import javax.swing.JTextArea; 
import javax.swing.JScrollPane;
import java.awt.GridLayout;

/** 
 * This class contains all of the properties that are responsible for creating and printing WordGUI objects.
 * Due Date: October 9, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 1
 */
public class WordGUI extends JFrame {

	/** 
	 * The WordGUI constructor runs the createAndShowGUI method.
	 * @param a1 the 1st array
	 * @param a2 the 2nd array
	 * @param a3 the 3rd array
	 */
	public WordGUI(String[] a1, String[] a2, String[] a3) {
		createAndShowGUI(a1, a2, a3); //This is so the program can get straight to creating and showing the WordGUI object.
	}
	
	/** 
	 * The createAndShowGUI method creates and shows the WordGUI file in 1 row and 3 columns.
	 * @param a1 the array in the 1st column
	 * @param a2 the array in the 2nd column
	 * @param a3 the array in the 3rd column
	 */
	public void createAndShowGUI(String[] a1, String[] a2, String[] a3) {
		
		JFrame myFrame = new JFrame("Project 1");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(400, 400);
		myFrame.setLocation(550, 300);
		myFrame.setLayout(new GridLayout(1, 3)); //1 row, 3 columns
		myFrame.setTitle("Project 1");
		
		JTextArea textArea1 = new JTextArea(25, 25);
		textArea1.setEditable(false); //This is so the user can't edit the text.
		JScrollPane scrollPane1 = new JScrollPane(textArea1);
		myFrame.getContentPane().add(scrollPane1); //This is how you make the JTextArea and JScrollPane visible to the user.
		
		JTextArea textArea2 = new JTextArea(25, 25);
		textArea2.setEditable(false); //This is so the user can't edit the text.
		JScrollPane scrollPane2 = new JScrollPane(textArea2);
		myFrame.getContentPane().add(scrollPane2); //This is how you make the JTextArea and JScrollPane visible to the user.
		
		JTextArea textArea3 = new JTextArea(25, 25);
		textArea3.setEditable(false); //This is so the user can't edit the text.
		JScrollPane scrollPane3 = new JScrollPane(textArea3);
		myFrame.getContentPane().add(scrollPane3); //This is how you make the JTextArea and JScrollPane visible to the user.
		
		myFrame.pack();
		myFrame.setVisible(true); //This line is important as this makes the frame able to be seen by the user.
		
		textArea1.append("All the Words:\n");
		for (int i = 0; i < a1.length; i++) {
			textArea1.append(a1[i] + "\n"); //This line makes sure the words are in their own lines
		}
		
		textArea2.append("The Valid Words:\n");
		for (int i = 0; i < a2.length; i++) {
			textArea2.append(a2[i] + "\n"); //This line makes sure the words are in their own lines
		}
		
		textArea3.append("The Invalid Words:\n");
		for (int i = 0; i < a3.length; i++) {
			textArea3.append(a3[i] + "\n"); //This line makes sure the words are in their own lines
		}
	} 
}