import javax.swing.*;
import java.awt.*;

/** 
 * The class that has the properties of the JFrame with an unsorted WordList and a sorted WordList.
 * Due Date: November 2nd, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 1
 */
public class WordGUI extends JFrame {

	/**
	 * The constructor; creates an empty JFrame.
	 */
	public WordGUI() {
		setTitle("Project3");
		setSize(600, 600);
		setLocation(700, 250);
		setLayout(new GridLayout(1, 3)); //The JMenu will be on top, and the three ArrayLists will be on the bottom, horizontally next to each other.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createJFrame();
		setVisible(true);
	}
	
	/**
	 * Creates a File menu containing the option to Open or Quit, with 3 JTextArea boxes.
	 */
	public void createJFrame() {
		JTextArea textArea1 = new JTextArea(10, 10);
		textArea1.setEditable(false);
		JScrollPane scrollPane1 = new JScrollPane(textArea1);
		getContentPane().add(scrollPane1, 1,0);
		scrollPane1.setVisible(false); //The scroll panes will not be visible at first because the JFrame needs to be empty with just the File menu when the user first launches the program. When the user chooses a text file, then they will appear.
		
		JTextArea textArea2 = new JTextArea(10, 10);
		textArea2.setEditable(false);
		JScrollPane scrollPane2 = new JScrollPane(textArea2);
		getContentPane().add(scrollPane2, 1,1);
		scrollPane2.setVisible(false); 
		
		JTextArea textArea3 = new JTextArea(10, 10);
		textArea3.setEditable(false);
		JScrollPane scrollPane3 = new JScrollPane(textArea3);
		getContentPane().add(scrollPane3, 1,2);
		scrollPane3.setVisible(false); 
		
		JMenuItem item;
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		FileMenuHandler fmh = new FileMenuHandler(this, textArea1, textArea2, textArea3, scrollPane1, scrollPane2, scrollPane3); //The user's choices in the JMenu must be handled by taking the same JFrame and its JTextAreas.
		
		item = new JMenuItem("Open");
		item.addActionListener(fmh);
		fileMenu.add(item);
		
		item = new JMenuItem("Quit");
		item.addActionListener(fmh);
		fileMenu.add(item);
		
		setJMenuBar(menuBar);
		menuBar.add(fileMenu, 0,0);
	}
}

