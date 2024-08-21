import javax.swing.JOptionPane;

/** 
 * @author Alyssa Ayala, lab section E
 * @date 09/25/2020
 * @version 1
 * @description a program that takes the user's sentence and finds the number of lower and upper case e's in it
 */

public class Project0 {
	
	/**
	 * @param args - the String array for the main method
	 * @return n/a
	 * @description the main method; JOptionPane boxes (what the user will see) are created here, and the program termination code is here too.
	 */
	
	public static void main(String[] args) {
		while (true) { //to keep the program running
			String sentence = JOptionPane.showInputDialog(null, "Please enter a sentence."); //where the user enters a sentence
			
			if (sentence.equalsIgnoreCase("STOP")) { //terminates the program if "STOP" (regardless of caps) is typed
				System.exit(0);
			}
			
			JOptionPane.showMessageDialog(null, "Number of lower case e's: " + eLowerCase(sentence) + "\nNumber of upper case e's: " + eUpperCase(sentence)); //where the number of lower case and upper case e's in the user's sentence is printed
		}
	}
	
	//Note: String.valueOf() converts the character into a string so that .equals() can be used.
	
	/**
	 * @param sentence - the user's sentence
	 * @return the number of lower case e's in the user's sentence 
	 * @description finds out how many lower case e's are in the user's sentence
	 */
	
	public static int eLowerCase(String sentence) {
		int lowerCaseCount = 0;
		for (int i = 0; i < sentence.length(); i++) {
			if (String.valueOf(sentence.charAt(i)).equals("e")) { //increment lowerCaseCount by 1 if the character at index i equals lower case e
				lowerCaseCount++;
			}
		}
		return lowerCaseCount;
	}
	
	/**
	 * @param sentence - the user's sentence
	 * @return the number of upper case e's in the user's sentence
	 * @description finds out how many upper case e's are in the user's sentence
	 */
	
	public static int eUpperCase(String sentence) {
		int upperCaseCount = 0;
		for (int i = 0; i < sentence.length(); i++) {
			if (String.valueOf(sentence.charAt(i)).equals("E")) { //increment upperCaseCount by 1 if the character at index i equals upper case e
				upperCaseCount++;
			}
		}
		return upperCaseCount;
	}
}