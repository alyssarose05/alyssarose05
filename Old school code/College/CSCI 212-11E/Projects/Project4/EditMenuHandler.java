import java.awt.event.*;
import javax.swing.*;

/**
 * A class that handles what happens when the user clicks on "Search" in the JMenu.
 * Due Date: December 9th, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 1
 */
public class EditMenuHandler implements ActionListener {
	protected WordGUI wordFrame;
	
	/**
	 * The constructor; takes a WordGUI object as an argument.
	 * @param wg the WordGUI that has access to the JTextArea containing the sorted Words.
	 */
	public EditMenuHandler(WordGUI wg) {
		wordFrame = wg;
	}
	
	/**
	 * Retrieves whether the user clicks on "Search," and runs the process of searching for a Word in the TreeMap if they do.
	 * @param event the option that the user chooses
	 */
	public void actionPerformed(ActionEvent event) {
		String menuName = event.getActionCommand();
		if (menuName.equals("Search")) {
			try {
				Word input = new Word((JOptionPane.showInputDialog(null, "Enter a Word:")).toString());
				if (wordFrame.sorted.containsKey(input))
					JOptionPane.showMessageDialog(null, "The Word, " + input + ", is in the TreeMap.");
				else
					JOptionPane.showMessageDialog(null, "The Word, " + input + ", is not in the TreeMap.");
			}
			catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "There is a null input."); //This is needed to prevent a NullPointerException in the console if the input is null.
			}
		}
	}
}