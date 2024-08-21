/**
 * This is the exception that runs if a Word is erroneous (it does not have just letters).
 * Due Date: December 9th, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 2
 */
public class IllegalWordException extends IllegalArgumentException {
	
	/**
	 * The constructor; runs the constructor of the IllegalArgumentException by displaying an error message in the same way it does.
	 * @param message the error message to be displayed to the user
	 */
	public IllegalWordException(String message) {
		super(message);
	}
}
