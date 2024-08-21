import javax.swing.*;
import java.awt.*;
import java.util.*;

/** 
 * The class that has the properties of the JFrame with an unsorted WordList and a sorted WordList.
 * The JFrame now has an Edit Menu that allows the user to search for a Word in the TreeMap.
 * Due Date: December 9th, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 2
 */
public class WordGUI extends JFrame {

	protected JTextArea textArea1, textArea2;
	protected JScrollPane scrollPane1, scrollPane2;
	protected ArrayList<Word> unsorted;
	protected TreeMap<Word, Integer> sorted;
	
	/**
	 * The constructor; creates an empty JFrame.
	 */
	public WordGUI() {
		setTitle("Project4");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(1, 2)); //The JMenu will be on top, and the three ArrayLists will be on the bottom, horizontally next to each other.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createJFrame();
		setVisible(true);
	}
	
	/**
	 * Creates a File menu containing the option to Open or Quit, with 3 JTextArea boxes.
	 */
	public void createJFrame() {
		textArea1 = new JTextArea(10, 10);
		textArea1.setEditable(false);
		scrollPane1 = new JScrollPane(textArea1);
		getContentPane().add(scrollPane1, 1,0);
		scrollPane1.setVisible(false); //The scroll panes cannot be visible at first because the JFrame needs to be empty with just the File menu when the user first launches the program. When the user chooses a text file, then they will appear.
		
		textArea2 = new JTextArea(10, 10);
		textArea2.setEditable(false);
		scrollPane2 = new JScrollPane(textArea2);
		getContentPane().add(scrollPane2, 1,1);
		scrollPane2.setVisible(false); 
		
		JMenuItem item;
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		FileMenuHandler fmh = new FileMenuHandler(this); //The user's choices in the JMenu must be handled by taking this WordGUI and all of its properties.
		EditMenuHandler emh = new EditMenuHandler(this); //We must take the JTextArea with the sorted Words to allow the user to search for a Word.

		item = new JMenuItem("Open");
		item.addActionListener(fmh);
		fileMenu.add(item);
		
		item = new JMenuItem("Quit");
		item.addActionListener(fmh);
		fileMenu.add(item);
		
		item = new JMenuItem("Search");
		item.addActionListener(emh);
		editMenu.add(item);

		setJMenuBar(menuBar);
		menuBar.add(fileMenu, 0,0);
		menuBar.add(editMenu, 0,1);
	}
}