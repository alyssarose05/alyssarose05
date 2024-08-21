/**
 * A class that tests the Polynomial class from Part A of the Warm Up Project.
 * @author Alyssa Ayala, CSCI 313-13
 * (not being handed in)
 */
public class WarmUpProjectA {
	
	/**
	 * The main method to test the Polynomial class. 
	 * @param args The arguments that would be implemented by the user, but they are not used here.
	 */
	public static void main(String[] args) {	
		Polynomial a = new Polynomial(4, 0);
		Polynomial b = new Polynomial(3,6);
		System.out.println(Polynomial.sum(a, b));
	}
}